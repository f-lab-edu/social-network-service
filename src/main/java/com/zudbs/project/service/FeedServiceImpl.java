package com.zudbs.project.service;

import com.zudbs.project.mapper.FeedMapper;
import com.zudbs.project.model.Feed;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class FeedServiceImpl implements FeedService {

    private FeedFileService feedFileService;

    private FeedMapper feedMapper;

    @Transactional
    @Override
    public void registerFeed(Feed feed, List<MultipartFile> files) {

        if (files.size() != 0) {
            feed.setHasFile(true);
        }

        feedMapper.registerFeed(feed);

        feedFileService.saveFiles(feed.getId(), files);
    }

    @Override
    public Feed getFeed(int feedId) {

        Feed feed = feedMapper.getFeed(feedId);

        loadFeedFile(feed);

        return feed;
    }

    @Override
    public List<Feed> getFeedList(String userId) {

        List<Feed> feedList = feedMapper.getFeedList(userId);

        for(Feed feed : feedList){

            loadFeedFile(feed);
        }

        return feedList;
    }

    public void loadFeedFile(Feed feed){

        if (feed.isHasFile()) {
            feed.setFiles(feedFileService.getFeedFiles(feed.getId()));
        }
    }


}
