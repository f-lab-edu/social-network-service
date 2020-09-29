package com.zudbs.project.service;

import com.zudbs.project.mapper.FeedFileMapper;
import com.zudbs.project.model.FeedFile;
import com.zudbs.project.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FeedFileServiceImpl implements FeedFileService {

    @Value("${uploadFilePath}")
    private String uploadFilePath;

    @Autowired
    private FeedFileMapper feedFileMapper;


    @Override
    public void saveFile(List<MultipartFile> files, int feedId) {

        for (int index = 0; index < files.size(); index++) {

            MultipartFile file = files.get(index);

            String saveFileName = String.format("%d_%s",feedId, file.getOriginalFilename());

            Path savePath = Paths.get(uploadFilePath, saveFileName);

            FileUtil.MultipartFileToFile(file, savePath);

            feedFileMapper.saveFeedFile(new FeedFile(index, feedId, file.getOriginalFilename(), savePath.toString(), file.getSize()));
        }

    }

}

