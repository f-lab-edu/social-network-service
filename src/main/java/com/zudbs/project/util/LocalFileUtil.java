package com.zudbs.project.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class LocalFileUtil implements FileUtil {

    @Override
    public void saveFile(MultipartFile multipartFile, Path path) {
        try {
            multipartFile.transferTo(new File(path.toString()));
        } catch (IOException ex) {

            String errorMessage = String.format("File : %s Path: %s 파일을 저장하는데 문제가 발생하였습니다."
                    , multipartFile.getOriginalFilename(), path);

            throw new RuntimeException(errorMessage);
        }
    }
}
