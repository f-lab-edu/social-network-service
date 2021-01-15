package com.zudbs.project.controller;

import com.zudbs.project.annotation.CheckLogin;
import com.zudbs.project.annotation.SessionVariable;
import com.zudbs.project.model.Feed;
import com.zudbs.project.model.FeedLike;
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
    public HttpStatus register(@SessionVariable(SessionKey.LOGIN_USER_ID) String userId, String content, List<MultipartFile> files) {

        Feed feed = new Feed(userId, LocalDateTime.now(), content, files.size() != 0);

        feedService.registerFeed(feed, files);

        return HttpStatus.CREATED;
    }

    @CheckLogin
    @GetMapping("/{userId}/{feedId}")
    public ResponseEntity<Feed> getFeed(@PathVariable String userId, @PathVariable int feedId) {

        Feed feed = feedService.getFeed(userId, feedId);

        return ResponseEntity.ok(feed);
    }

    @CheckLogin
    @GetMapping
    public ResponseEntity<List<Feed>> getFeedList(@RequestParam String userId) {

        List<Feed> feedList = feedService.getFeedList(userId);

        return ResponseEntity.ok(feedList);
    }

    @CheckLogin
    @PostMapping("/{userId}/{feedId}")
    public HttpStatus updateFeed(@PathVariable String userId, @PathVariable int feedId, String content, List<MultipartFile> files) {

        Feed feed = new Feed(feedId, userId, LocalDateTime.now(), content, files.size() != 0);

        feedService.updateFeed(feed, files);

        return HttpStatus.OK;
    }

    @CheckLogin
    @DeleteMapping("/{userId}/{feedId}")
    public HttpStatus deleteFeed(@PathVariable String userId, @PathVariable int feedId) {

        feedService.deleteFeed(userId, feedId);

        return HttpStatus.OK;
    }

    @CheckLogin
    @PostMapping("/likes/{feedId}")
    public HttpStatus addFeedLike(@SessionVariable(SessionKey.LOGIN_USER_ID) String userId, @PathVariable int feedId) {

        feedService.addFeedLike( new FeedLike(feedId,userId));

        return HttpStatus.OK;
    }

    @CheckLogin
    @DeleteMapping("/likes/{feedId}")
    public HttpStatus deleteFeedLike(@SessionVariable(SessionKey.LOGIN_USER_ID) String userId, @PathVariable int feedId) {

        feedService.deleteFeedLike(new FeedLike(feedId,userId));

        return HttpStatus.OK;
    }



}