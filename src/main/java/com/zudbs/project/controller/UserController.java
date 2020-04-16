package com.zudbs.project.controller;


import com.zudbs.project.model.User;
import com.zudbs.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/join")
    public String joinUser(@ModelAttribute User user) {

        userService.joinUser(user);

        return "Sucess";
    }

}
