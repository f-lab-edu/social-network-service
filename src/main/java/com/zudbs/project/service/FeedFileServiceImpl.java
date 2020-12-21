package com.zudbs.project.service;

import com.zudbs.project.mapper.FeedFileMapper;
import com.zudbs.project.model.FeedFile;
import com.zudbs.project.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedFileServiceImpl implements FeedFileService {

    @Value("${uploadFilePath}")
    private String uploadFilePath;

    @Autowired
    private FeedFileMapper feedFileMapper;

    @Autowired
    private FileUtil fileUtil;

    @Override
    public void saveFiles(int feedId, List<MultipartFile> files) {

        for (int index = 0; index < files.size(); index++) {

            MultipartFile file = files.get(index);

            String saveFileName = String.format("%d_%d_%s", feedId, index, file.getOriginalFilename());

            Path savePath = Paths.get(uploadFilePath, saveFileName);

            fileUtil.saveFile(file, savePath);

            feedFileMapper.saveFeedFile(new FeedFile(index, feedId, file.getOriginalFilename(), savePath.toString(), file.getSize()));
        }

    }

    @Override
    public List<File> getFeedFiles(int feedId) {

        List<FeedFile> feedFiles = feedFileMapper.getFeedFiles(feedId);

        List<File> files = feedFiles.stream().map(feedFile -> {
            return new File(feedFile.getStoredFileName());
        }).collect(Collectors.toList());

        return files;
    }

    @Override
    public void updateFeedFiles(int feedId, List<MultipartFile> files) {

        feedFileMapper.removeFeedFiles(feedId);

        saveFiles(feedId, files);

    }

    @Override
    public void deleteFeedFiles(int feedId) {
        feedFileMapper.removeFeedFiles(feedId);
    }

}

