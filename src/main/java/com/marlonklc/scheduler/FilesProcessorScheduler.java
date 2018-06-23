package com.marlonklc.scheduler;

import com.marlonklc.factory.SummaryFactory;
import com.marlonklc.model.DataAnalysis;
import com.marlonklc.service.ExtractDataAnalysisService;
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
import java.util.function.Predicate;

@Component
public class FilesProcessorScheduler {

    private static final Logger log = LoggerFactory.getLogger(FilesProcessorScheduler.class);

    @Value("${app.files.directory.in}")
    private String directoryIn;

    @Value("${app.files.directory.out}")
    private String directoryOut;

    @Autowired
    private FilesStoreService filesStoreService;

    @Autowired
    private ExtractDataAnalysisService extractDataAnalysisService;

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
            DataAnalysis dataAnalysis = extractDataAnalysis(path);

            processDataAndFinish(path, dataAnalysis);
        } catch (IOException ex) {
            log.error("Error on process file", ex);
        }
    }

    private DataAnalysis extractDataAnalysis(Path path) throws IOException {
        log.debug("Started to extract data of file: " + path.getFileName());

        return extractDataAnalysisService.processFile(path);
    }

    private void processDataAndFinish(Path path, DataAnalysis dataAnalysis) throws IOException {
        String filenameDone = filesStoreService.getFilenameDone(path);

        Path pathOut = Paths.get(directoryOut, filenameDone);

        SummaryFactory summaryFactory = SummaryFactory.ofDefault(dataAnalysis);

        Files.write(pathOut, summaryFactory.getSummary());

        filesStoreService.store(path);

        log.debug("Finished process file: " + path.getFileName());
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
