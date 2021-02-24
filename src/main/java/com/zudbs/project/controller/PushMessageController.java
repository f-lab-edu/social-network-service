package com.zudbs.project.controller;

import com.zudbs.project.annotation.CheckLogin;
import com.zudbs.project.service.PushMessageService;
import com.zudbs.project.util.SessionKeys;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.*;

import static com.zudbs.project.util.SessionKeys.USER_DEVICE_TOKEN;

@AllArgsConstructor
@RestController
@RequestMapping("/notifications")
public class PushMessageController {

    private PushMessageService pushMessageService;

    @CheckLogin
    @PostMapping
    public HttpStatus registerToken(@RequestBody String token, HttpSession httpSession) {

        String userID = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);

        pushMessageService.registerReceiver(userID, token);

        return HttpStatus.OK;
    }

    @CheckLogin
    @DeleteMapping
    public HttpStatus removeToken(@RequestBody String token, HttpSession httpSession) {

        String userID = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);

        pushMessageService.removeReceiver(userID);

        return HttpStatus.OK;
    }


}
