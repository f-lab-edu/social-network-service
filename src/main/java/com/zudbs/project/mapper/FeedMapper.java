package com.zudbs.project.mapper;

import com.zudbs.project.model.Feed;
import com.zudbs.project.model.FeedComment;
import com.zudbs.project.model.FeedLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FeedMapper {
    void registerFeed(Feed feed);

    Feed getFeed(int feedId);

    List<Feed> getFeedList(String userId);

    void updateFeed(Feed feed);

    void deleteFeed(String userId, int feedId);

    void addFeedLike(FeedLike like);

    void cancelFeedLike(FeedLike like);

    int getFeedLikesCount(int feedId);

    void addFeedCommnet(FeedComment comment);
}
