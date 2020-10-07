package com.zudbs.project.mapper;

import com.zudbs.project.model.Feed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    void registerFeed(Feed feed);

    Feed getFeed(String userId, int feedId);

    List<Feed> getFeedList(String userId);

    void updateFeed(Feed feed);

    void deleteFeed(String userId, int feedId);
}
