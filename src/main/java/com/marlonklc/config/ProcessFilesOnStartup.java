package com.marlonklc.config;

import com.marlonklc.service.FilesStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ProcessFilesOnStartup {

    private static final Logger log = LoggerFactory.getLogger(ProcessFilesOnStartup.class);

    @Value("${app.files.directory.in}")
    private String directoryIn;

    @Value("${app.files.directory.out}")
    private String directoryOut;

    @Value("${app.filename.done}")
    private String filenameDone;

    @Autowired
    private FilesStoreService filesStoreService;

    @PostConstruct
    public void init(){
        try {
            if (checkExistDirectories()) {
                Path outPath = Paths.get(directoryOut);

                Files.list(outPath)
                        .filter(path -> path.toString().endsWith(filenameDone))
                        .forEach(this::done);
            }
        } catch (IOException ex) {
            log.error("Error on init file store", ex);
        }
    }

    private void done(Path path) {
        filesStoreService.store(path);
    }

    private boolean checkExistDirectories() {
        Path pathIn = Paths.get(directoryIn);
        Path pathOut = Paths.get(directoryOut);

        if (Files.notExists(pathIn) || Files.notExists(pathOut)) {
            log.warn("Both in/out directories must exists!");
            return false;
        }

        return true;
    }

}
