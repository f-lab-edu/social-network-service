package com.zudbs.project.util;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileUtil {

    void saveFile(MultipartFile multipartFile, Path path);
}
