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

    /*
    @Resource  (이름 -> 타입) 주입(DI) 자바 지원    => 프레임워크 종속 X
    @Autowired (타입 -> 이름) 주입(DI) 스프링 지원  => 프레임워크 종속 O ( @Qualifier 이름으로 강제 연결)
    @Inject    (타입 -> 이름) 주입(DI) 자바 지원    => 프레임워크 종속 X

    타입을 많을 경우 타입으로 연결하는 어노테이션은 오류날 확률이 올라간다.
    프레임워크에 종속된 어노테이션을 사용할 경우 프레임워크를 변경할 경우 수정사항이 많아진다.
    */
    @Autowired
    FriendService friendService;

    @CheckLogin
    @PostMapping("requests/{friendId}")  /*@PathVariable 메소드 매개 변수가 URI 템플릿 변수에 바인드된다는 어노테이션*/
    public HttpStatus requestFriend(@PathVariable String friendId, HttpSession httpSession) {

        String userId = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);

        friendService.requestFriend(userId, friendId);

        return HttpStatus.CREATED;
    }

    @CheckLogin
    @PutMapping("requests/{requestId}")
    public HttpStatus acceptFriend(@PathVariable String requestId, HttpSession httpSession) {

        String userID = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);

        friendService.acceptFriend(requestId, userID);

        return HttpStatus.OK;
    }

    @CheckLogin
    @DeleteMapping("requests/{requestId}")
    public HttpStatus rejectFriend(@PathVariable String requestId, HttpSession httpSession) {

        String userID = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);

        friendService.rejectFriend(requestId, userID);

        return HttpStatus.OK;
    }

    @CheckLogin
    @PutMapping("follows/{requestId}")
    public HttpStatus followFriend(@PathVariable String requestId, @RequestParam boolean follow, HttpSession httpSession) {

        String userId = (String) httpSession.getAttribute(SessionKeys.LOGIN_USER_ID);

        friendService.followFriend(requestId, userId, follow);

        return HttpStatus.OK;
    }

}

