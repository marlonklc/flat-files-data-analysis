package com.marlonklc.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileProcessorScheduler {

    private static final Logger log = LoggerFactory.getLogger(FileProcessorScheduler.class);

    @Value("${app.files.directory.in}")
    private String directoryIn;

    @Value("${app.files.directory.out}")
    private String directoryOut;

    public FileProcessorScheduler() {
    }

    @Scheduled(initialDelayString = "${app.job.scheduler.delay}", fixedRateString = "${app.job.scheduler.interval}")
    public void startProcessFiles() {
        try {
            if (checkExistDirectories()) {
                log.debug("Started processing of the files...");

                Path pathIn = Paths.get(directoryIn);

                Files.list(pathIn)
                        .forEach(this::processFile);
            }
        } catch (IOException ex) {
            log.error("Error on start process files", ex);
        }
    }

    private void processFile(Path path) {
        try {
            log.debug("Processing file: " + path.getFileName());

            // do process file service

            String fileName = path.getFileName() + ".done";

            Path pathOut = Paths.get(directoryOut, fileName);

            Files.write(pathOut, new byte[0]);
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
