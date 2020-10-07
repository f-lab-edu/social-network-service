package com.zudbs.project.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FeedFileService {

    void saveFiles(int feedId, List<MultipartFile> files);

    List<File> getFeedFiles(int feedId);
}
