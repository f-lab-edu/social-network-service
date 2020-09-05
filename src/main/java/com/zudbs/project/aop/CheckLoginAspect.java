package com.zudbs.project.aop;

import com.zudbs.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpSession;

@Component
/*
@Aspect 어노테이션은 Aspectj를 사용하는 클래스를 명시해주는 것이며
Spring Framework에 종속되지 않은 클래스이므로 빈으로 자동 등록되지 않는다.
따라서 Spring Aop를 이용하기 위해서는 빈으로 따로 등록하는 과정이 필요하다.
*/

@Aspect
/*해당 클래스가 부가기능을 담당하는 클래스라는 것을 나타내는 어노테이션*/

public class CheckLoginAspect {

    @Autowired
    private HttpSession session;
    /*
    CheckLoginAspect는 Scope가 Singleton 빈이지만 HttpSession은 Scope가 Session이다.
    일반적으로 Singleton빈이 SessionScope빈을 DI하면 DI 된 객체도 한 번 밖에 세팅되지 않기 때문에
    Scope가 Singleton으로 한정된다.

    하지만 Spring은 HttpSession을 SessionObjectFactory 객체로 빈으로 등록한다.
    SessionObjectFactory는 빈 객체를 생성하는 ObjectFactory<T>를 구현한 클래스이다.

    ObjectFactory<T>는 DL방식으로 빈을 주입해준다. DL이란 빈 객체를 생성하는 ObjectFactory (Proxy 역할을 함)를
    DI 해 두고, 객체가 필요한 시점에 getObject 메서드를 호출하는 방식이다.
    */

    @Before("@annotation(com.zudbs.project.annotation.CheckLogin)")
    public void checkLogin(JoinPoint joinPoint) throws Throwable {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED) {
            };
        }
    }
}