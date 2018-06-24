package com.marlonklc.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class FilesUtil {

    @Value("${app.filename.readonly}")
    private String filenameReadonly;

    @Value("${app.filename.done}")
    private String filenameDone;

    @Value("${app.files.directory.in}")
    private String directoryIn;

    @Value("${app.files.directory.out}")
    private String directoryOut;

    public String getFilenameReadonly() {
        return filenameReadonly;
    }

    public String getFilenameDone() {
        return filenameDone;
    }

    public String getDirectoryIn() {
        return directoryIn;
    }

    public String getDirectoryOut() {
        return directoryOut;
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
}
