package com.zudbs.project.controller;

import com.zudbs.project.model.User;
import com.zudbs.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/*
해당 클래스를 웹 요청을 처리하는 컨트롤러로 사용
요청 매핑 메소드에 @ResponseBody 추가
@ResponseBody : POJO를 Http Response Body로 매핑
*/
@RestController
@RequestMapping("/users") /* 요청 URL과 매핑하기위한 어노테이션 */
public class UserController {

    @Autowired /* Bean을 자동으로 주입해주기 위한 어노테이션 */
    private UserService userService;

    @PostMapping("/join") /* @RequestMapping + RequestMethod.POST */
    public HttpStatus joinUser(@ModelAttribute User user) {

        userService.joinUser(user);

        return HttpStatus.CREATED;
    }

    @PostMapping("/delete")
    public HttpStatus deleteUser(@ModelAttribute User user) {
        boolean result = userService.deleteUser(user);

        if (result) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PostMapping("/login")
    public HttpStatus login(@ModelAttribute User user, HttpSession httpSession) {

        try {
            userService.login(user, httpSession);
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.OK;
    }


}
