package com.zudbs.project.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    public static void MultipartFileToFile(MultipartFile file, Path path) {
        try {
            file.transferTo(new File(path.toString()));

        } catch (IOException ex) {

        }
    }

}
