package com.marlonklc.service;

import com.marlonklc.util.FilesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilesStoreService {

    private static final Logger LOG = LoggerFactory.getLogger(FilesStoreService.class);

    private List<String> filesDone = new ArrayList<>();

    @Autowired
    private FilesUtil filesUtil;

    public void store(Path path) {
        filesDone.add(path.getFileName().toString());
    }

    public boolean isNewFile(Path path) {
        return !filesDone.contains(path.getFileName().toString());
    }

    public boolean checkExistDirectories() {
        Path pathIn = Paths.get(filesUtil.getDirectoryIn());
        Path pathOut = Paths.get(filesUtil.getDirectoryOut());

        if (Files.notExists(pathIn) || Files.notExists(pathOut)) {
            LOG.warn("Both in/out directories must exists!");
            return false;
        }

        return true;
    }
}
