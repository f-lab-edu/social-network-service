package com.zudbs.project.service;

import com.zudbs.project.model.Feed;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedService {

    void registerFeed(Feed feed, List<MultipartFile> files);

    Feed getFeed(int feedId);

    List<Feed> getFeedList(String userId);
}
