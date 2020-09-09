package com.zudbs.project.controller;

import com.zudbs.project.service.FriendService;
import com.zudbs.project.util.SessionKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}