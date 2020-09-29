package com.zudbs.project.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedFileService {

    void saveFile(List<MultipartFile> files, int feedId);
}
