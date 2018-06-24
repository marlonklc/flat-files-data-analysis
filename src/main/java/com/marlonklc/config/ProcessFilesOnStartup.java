package com.marlonklc.config;

import com.marlonklc.service.FilesStoreService;
import com.marlonklc.util.FilesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Order(99)
public class ProcessFilesOnStartup {

    private static final Logger log = LoggerFactory.getLogger(ProcessFilesOnStartup.class);

    @Autowired
    private FilesStoreService filesStoreService;

    @Autowired
    private FilesUtil filesUtil;

    @PostConstruct
    public void init(){
        try {
            if (filesStoreService.checkExistDirectories()) {
                Path outPath = Paths.get(filesUtil.getDirectoryOut());

                Files.list(outPath)
                        .filter(path -> path.toString().endsWith(filesUtil.getFilenameDone()))
                        .forEach(this::done);
            }
        } catch (IOException ex) {
            log.error("Error on init file store", ex);
        }
    }

    private void done(Path pathOut) {
        String filenameIn = pathOut.getFileName()
                .toString()
                .replace(filesUtil.getFilenameDone(), filesUtil.getFilenameReadonly());

        Path pathIn = Paths.get(filesUtil.getDirectoryIn(), filenameIn);
        filesStoreService.store(pathIn);
    }
}
