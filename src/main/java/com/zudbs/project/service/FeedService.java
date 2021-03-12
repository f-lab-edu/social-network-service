package com.zudbs.project.service;

import com.zudbs.project.model.Feed;
import com.zudbs.project.model.FeedComment;
import com.zudbs.project.model.FeedLike;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedService {

    void registerFeed(Feed feed, List<MultipartFile> files);

    Feed getFeed(String userId, int feedId);

    List<Feed> getFeedList(String userId);

    void updateFeed(Feed feed, List<MultipartFile> files);

    void deleteFeed(String userId, int feedId);

    void addFeedLike(FeedLike feedLike);

    void cancelFeedLike(FeedLike feedLike);

    void addFeedComment(FeedComment feedComment);

    void deleteFeedComment(int commentId);

}
