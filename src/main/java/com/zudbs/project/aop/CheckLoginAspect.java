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

    @Before("@annotation(com.zudbs.project.annotation.CheckLogin)")
    public void checkLogin(JoinPoint joinPoint) throws Throwable {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED){};
        }
    }
}