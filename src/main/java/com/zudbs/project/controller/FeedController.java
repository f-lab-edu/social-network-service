package com.zudbs.project.controller;

import com.zudbs.project.annotation.CheckLogin;
import com.zudbs.project.annotation.SessionVariable;
import com.zudbs.project.model.Feed;
import com.zudbs.project.service.FeedService;
import com.zudbs.project.util.SessionKey;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/feeds")
public class FeedController {

    private FeedService feedService;

    @CheckLogin
    @PostMapping
    public HttpStatus register(@SessionVariable(SessionKey.LOGIN_USER_ID) String userId, Feed feed, List<MultipartFile> files) {

        feed.setUserId(userId);
        feed.setDateTime(LocalDateTime.now());

        feedService.registerFeed(feed, files);

        return HttpStatus.CREATED;
    }

    @CheckLogin
    @GetMapping("/{feedId}")
    public ResponseEntity<Feed> getFeed(@PathVariable int feedId) {

        Feed feed = feedService.getFeed(feedId);

        return ResponseEntity.ok(feed);
    }

    @CheckLogin
    @GetMapping("/{userId}")
    public ResponseEntity< List<Feed> > getFeedList(@PathVariable String userId) {

        List<Feed> feedList = feedService.getFeedList(userId);

        return ResponseEntity.ok(feedList);
    }

}