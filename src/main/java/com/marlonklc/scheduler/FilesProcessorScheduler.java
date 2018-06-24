package com.marlonklc.scheduler;

import com.marlonklc.factory.SummaryFactory;
import com.marlonklc.model.DataAnalysis;
import com.marlonklc.service.ExtractDataAnalysisService;
import com.marlonklc.service.FilesStoreService;
import com.marlonklc.util.FilesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

@Component
public class FilesProcessorScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(FilesProcessorScheduler.class);

    @Autowired
    private FilesUtil filesUtil;

    @Autowired
    private FilesStoreService filesStoreService;

    @Autowired
    private ExtractDataAnalysisService extractDataAnalysisService;

    @Scheduled(initialDelayString = "${app.job.scheduler.delay}", fixedRateString = "${app.job.scheduler.interval}")
    public void startProcessFiles() {
        try {
            if (filesStoreService.checkExistDirectories()) {
                LOG.debug(">> Started processing of the files...");

                Path pathIn = Paths.get(filesUtil.getDirectoryIn());

                Files.list(pathIn)
                        .filter(fileIsValidAndNew())
                        .forEach(this::processFile);

                LOG.debug(">> Finished processing of the files!");
            }
        } catch (IOException ex) {
            LOG.error("Error on start process files", ex);
        }
    }

    private Predicate<Path> fileIsValidAndNew() {
        return path -> filesUtil.isValidFile(path) && filesStoreService.isNewFile(path);
    }

    private void processFile(Path path) {
        try {
            LOG.debug("Extracting data of file: " + path.getFileName());

            DataAnalysis dataAnalysis = extractDataAnalysisService.processFile(path);

            SummaryFactory summaryFactory = SummaryFactory.ofDefault(dataAnalysis);

            processStore(path, summaryFactory);
        } catch (IOException ex) {
            LOG.error("Error on process file", ex);
        }
    }

    private void processStore(Path path, SummaryFactory summaryFactory) throws IOException {
        String filenameDone = filesUtil.getFilenameDone(path);

        Path pathOut = Paths.get(filesUtil.getDirectoryOut(), filenameDone);

        Files.write(pathOut, summaryFactory.getSummary());

        LOG.debug("Writed on file: " + filenameDone);

        filesStoreService.store(path);

        LOG.debug("Stored file: " + path.getFileName());
    }
}
