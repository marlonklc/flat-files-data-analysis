package com.marlonklc.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilesStoreService {

    private List<String> filesDone = new ArrayList<>();

    @Value("${app.filename.readonly}")
    private String filenameReadonly;

    @Value("${app.filename.done}")
    private String filenameDone;

    public void store(Path path) {
        filesDone.add(getFilenameDone(path));
    }

    public String getFilenameDone(Path path) {
        return getFilenameWithoutExtension(path) + filenameDone;
    }

    private String getFilenameWithoutExtension(Path path) {
        String fileName = path.getFileName().toString();
        return fileName.substring(0, fileName.length() - filenameReadonly.length());
    }

    public boolean isValidFile(Path path) {
        return path.getFileName().toString().endsWith(filenameReadonly);
    }

    public boolean isNewFile(Path path) {
        return !filesDone.contains(getFilenameDone(path));
    }
}
