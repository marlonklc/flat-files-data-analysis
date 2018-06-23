package com.marlonklc.scheduler;

import com.marlonklc.service.FilesStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.function.Predicate;

@Component
public class FileProcessorScheduler {

    private static final Logger log = LoggerFactory.getLogger(FileProcessorScheduler.class);

    @Value("${app.files.directory.in}")
    private String directoryIn;

    @Value("${app.files.directory.out}")
    private String directoryOut;

    @Autowired
    private FilesStoreService filesStoreService;

    @Scheduled(initialDelayString = "${app.job.scheduler.delay}", fixedRateString = "${app.job.scheduler.interval}")
    public void startProcessFiles() {
        try {
            if (checkExistDirectories()) {
                log.debug("Started processing of the files...");

                Path pathIn = Paths.get(directoryIn);

                Files.list(pathIn)
                        .filter(isValidAndNew())
                        .forEach(this::processFile);
            }
        } catch (IOException ex) {
            log.error("Error on start process files", ex);
        }
    }

    private Predicate<Path> isValidAndNew() {
        return path -> filesStoreService.isValidFile(path) && filesStoreService.isNewFile(path);
    }

    private void processFile(Path path) {
        try {
            log.debug("Started to process file: " + path.getFileName());

            // do process file service
            // TODO - do stuff

            String filenameDone = filesStoreService.getFilenameDone(path);

            Path pathOut = Paths.get(directoryOut, filenameDone);

            // write data analysis
            Files.write(pathOut, ("finish process file:" + filenameDone + "\n" + LocalDateTime.now().toString()).getBytes());

            filesStoreService.store(path);

            log.debug("Finished process file: " + path.getFileName());
        } catch (IOException ex) {
            log.error("Error on process file", ex);
        }
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
