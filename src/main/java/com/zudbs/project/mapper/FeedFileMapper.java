package com.zudbs.project.mapper;

import com.zudbs.project.model.Feed;
import com.zudbs.project.model.FeedFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedFileMapper {
    void saveFeedFile(FeedFile file);
}
