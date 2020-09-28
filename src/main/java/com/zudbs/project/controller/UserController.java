package com.zudbs.project.controller;

import com.zudbs.project.model.User;
import com.zudbs.project.service.UserService;
import com.zudbs.project.service.FCMService;
import com.zudbs.project.util.SessionKeys;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
/*
요청 매핑 메소드에 @ResponseBody 추가

@Controller : 자바 서버의 서블릿컨테이너는 HTTP 프로토콜을 통해 들어온 요청이 DispatcherServlet에 할당된 것이라면
              해당 요청 정보를 DIspathcerServlet에 전달합니다. 요청을 받은 DistpatcherServlet은 모든 요청에 대해
              공통적으로 처리 되어야 만하는 작업들을 수행한 후 (보안, 한글 디코딩) 요청에 맞는 작업을 처리하기 위해
              HandlerMapping 객체에게 컨트롤러 검색을 요청합니다. HandlerMapping은 클라이언트의 요청 경로를 이용
              해서 이를 처리할 컨트롤러(@controller)를 찾아서 DispatcherServlet에 리턴합니다. DispathcerServlet은
              컨트롤러를 실행하기 위해 HandlerAdapter 객체에게 요청 처리를 위임합니다. HandlerAdapter객체는 컨트롤러의
              알맞은 메소드를 호출해서 요청을 처리합니다.

@ResponseBody : 리턴하는 객체를 JSON 형식으로 변환하여 Http Response의 body로 작성해주는 어노테이션
*/
@RequestMapping("/users") /* 요청 URL과 매핑하기위한 어노테이션 */
public class UserController {

    private UserService userService;
    private FCMService fcmService;

    public UserController(UserService userService, FCMService fcmService) {
        this.userService = userService;
        this.fcmService = fcmService;
    }


    @PostMapping("/join") /* @RequestMapping + RequestMethod.POST */
    public HttpStatus joinUser(@ModelAttribute User user) {

        userService.joinUser(user);

        return HttpStatus.CREATED;
    }

    @PostMapping("/delete")
    public HttpStatus deleteUser(@ModelAttribute User user) {

        try {
            userService.deleteUser(user);
        } catch (Exception e) {

            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.OK;
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

    @GetMapping("/logout")
    public HttpStatus logout(HttpSession httpSession) {

        String userId = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);
        fcmService.removeToken(userId);

        httpSession.invalidate();

        return HttpStatus.OK;
    }

}
