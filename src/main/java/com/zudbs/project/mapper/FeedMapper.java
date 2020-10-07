package com.zudbs.project.mapper;

import com.zudbs.project.model.Feed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    void registerFeed(Feed feed);

    Feed getFeed(int feedId);

    List<Feed> getFeedList(String userId);

}
