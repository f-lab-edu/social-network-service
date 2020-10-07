package com.zudbs.project.mapper;

import com.zudbs.project.model.Feed;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
    void registerFeed(Feed feed);

    Feed getFeed(int feedId);

}
