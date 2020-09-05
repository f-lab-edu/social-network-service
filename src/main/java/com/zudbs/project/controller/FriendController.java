package com.zudbs.project.controller;

import com.zudbs.project.model.User;
import com.zudbs.project.service.FriendService;
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

        User user = (User)httpSession.getAttribute("user");

        friendService.requestFriend(user.getUserID(),friendId);

        return HttpStatus.CREATED;
    }
}