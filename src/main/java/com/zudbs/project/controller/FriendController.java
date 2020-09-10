package com.zudbs.project.controller;

import com.zudbs.project.annotation.CheckLogin;
import com.zudbs.project.service.FriendService;
import com.zudbs.project.util.SessionKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    FriendService friendService;

    /*@PathVariable 메소드 매개 변수가 URI 템플릿 변수에 바인드된다는 어노테이션*/
    @PostMapping("request/{friendId}")
    public HttpStatus requestFriend(@PathVariable String friendId, HttpSession httpSession) {

        String userId = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);

        friendService.requestFriend(userId, friendId);

        return HttpStatus.CREATED;
    }

    @CheckLogin
    @PutMapping("request/{requestId}")
    public HttpStatus acceptFriend(@PathVariable String requestId, HttpSession httpSession) {

        String userID = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);

        friendService.acceptFriend(requestId, userID);

        return HttpStatus.OK;
    }

    @CheckLogin
    @DeleteMapping("request/{requestId}")
    public HttpStatus rejectFriend(@PathVariable String requestId, HttpSession httpSession) {

        String userID = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);

        friendService.rejectFriend(requestId, userID);

        return HttpStatus.OK;
    }

    @PutMapping("follow/{requestId}")
    public HttpStatus followFriend(@PathVariable String requestId, @RequestParam boolean follow, HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");

        friendService.followFriend(requestId, user.getUserID(), follow);

        return HttpStatus.OK;
    }

}

