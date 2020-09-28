package com.zudbs.project.controller;

import com.zudbs.project.annotation.CheckLogin;
import com.zudbs.project.service.FCMService;
import com.zudbs.project.util.SessionKeys;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/notifications")
public class FCMController {

    private FCMService fcmService;

    public FCMController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @CheckLogin
    @PostMapping("register")
    public HttpStatus registerToken(@RequestBody String token, HttpSession httpSession) {

        String userID = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);
        fcmService.registerToken(userID, token);

        return HttpStatus.OK;
    }


}
