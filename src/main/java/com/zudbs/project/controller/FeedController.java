package com.zudbs.project.controller;

import com.zudbs.project.annotation.CheckLogin;
import com.zudbs.project.model.Feed;
import com.zudbs.project.service.FeedService;
import com.zudbs.project.util.SessionKeys;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/feeds")
public class FeedController {

    private FeedService feedService;


    @CheckLogin
    @PostMapping
    public HttpStatus register(Feed feed, List<MultipartFile> files, HttpSession httpSession) {

        String userId = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);

        feed.setUserId(userId);
        feed.setDateTime(LocalDateTime.now());

        feedService.registerFeed(feed, files);

        return HttpStatus.CREATED;
    }

}