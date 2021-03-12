package com.zudbs.project.service;

import com.zudbs.project.mapper.FeedMapper;
import com.zudbs.project.model.Feed;
import com.zudbs.project.model.FeedComment;
import com.zudbs.project.model.FeedLike;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Key;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

    private FeedFileService feedFileService;

    private FeedMapper feedMapper;

    @Transactional
    @Override
    public void registerFeed(Feed feed, List<MultipartFile> files) {

        feedMapper.registerFeed(feed);

        feedFileService.saveFiles(feed.getId(), files);
    }

    @Cacheable(value = "feeds", key = "#feedId")
    @Override
    public Feed getFeed(String userId, int feedId) {

        Feed feed = feedMapper.getFeed(feedId);

        loadFeedFile(feed);

        return feed;
    }

    @Cacheable(value = "feeds", key = "#userId")
    @Override
    public List<Feed> getFeedList(String userId) {

        List<Feed> feedList = feedMapper.getFeedList(userId);

        for (Feed feed : feedList) {

            loadFeedFile(feed);
        }

        return feedList;
    }


    @Transactional
    @Override
    public void updateFeed(Feed feed, List<MultipartFile> files) {

        feedMapper.updateFeed(feed);

        feedFileService.updateFeedFiles(feed.getId(), files);
    }

    @Transactional
    @Override
    public void deleteFeed(String userId, int feedId) {

        feedMapper.deleteFeed(userId, feedId);

        feedFileService.deleteFeedFiles(feedId);
    }

    public void loadFeedFile(Feed feed) {

        if (feed.isHasFile()) {
            feed.setFiles(feedFileService.getFeedFiles(feed.getId()));
        }
    }

    @Override
    public void addFeedLike(FeedLike like) {

        feedMapper.addFeedLike(like);
    }

    @Override
    public void cancelFeedLike(FeedLike like) {
        feedMapper.cancelFeedLike(like);
    }

    @Override
    public void addFeedComment(FeedComment comment) {

        feedMapper.addFeedComment(comment);
    }

    @Override
    public void deleteFeedComment(int commentId) {

        feedMapper.deleteFeedComment(commentId);
    }

}
