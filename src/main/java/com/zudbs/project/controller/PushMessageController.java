package com.zudbs.project.controller;

import com.zudbs.project.annotation.CheckLogin;
import com.zudbs.project.annotation.SessionVariable;
import com.zudbs.project.service.PushMessageService;
import com.zudbs.project.util.SessionKey;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/notifications")
public class PushMessageController {

    private PushMessageService pushMessageService;

    @CheckLogin
    @PostMapping
    public HttpStatus registerToken(@SessionVariable(SessionKey.LOGIN_USER_ID) String userId, @RequestBody String token) {

        pushMessageService.registerReceiver(userId, token);

        return HttpStatus.OK;
    }

    @CheckLogin
    @DeleteMapping
    public HttpStatus removeToken(@SessionVariable(SessionKey.LOGIN_USER_ID) String userId, @RequestBody String token) {

        pushMessageService.removeReceiver(userId);

        return HttpStatus.OK;
    }


}
