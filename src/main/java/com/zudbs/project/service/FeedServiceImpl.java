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

        feedMapper.registerFeed(feed);

        feedFileService.saveFiles(feed.getId(), files);
    }

    @Override
    public Feed getFeed(int feedId) {

        Feed feed = feedMapper.getFeed(feedId);

        if (feed.isHasFile()) {
            feed.setFiles(feedFileService.getFeedFiles(feedId));
        }

        return feed;
    }

}
