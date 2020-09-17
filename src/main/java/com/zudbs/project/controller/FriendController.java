package com.zudbs.project.controller;

import com.zudbs.project.annotation.CheckLogin;
import com.zudbs.project.annotation.SessionVariable;
import com.zudbs.project.service.FriendService;
import com.zudbs.project.util.SessionKey;
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
    public HttpStatus requestFriend(@SessionVariable(SessionKey.LOGIN_USER_ID) String userId, @PathVariable String friendId) {

        friendService.requestFriend(userId, friendId);

        return HttpStatus.CREATED;
    }
}