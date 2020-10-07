package com.zudbs.project.mapper;

import com.zudbs.project.model.FeedFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedFileMapper {

    void saveFeedFile(FeedFile file);

    List<FeedFile> getFeedFiles(int feedId);
}
