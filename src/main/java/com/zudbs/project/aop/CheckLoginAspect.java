package com.zudbs.project.aop;

import com.zudbs.project.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint ;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpSession;

@Component // @Aspect 만으로는 빈으로 등록되지 않기때문에 빈으로 등록하기 위한 어노테이션
@Aspect // 해당 클래스가 부가기능을 담당하는 클래스라는 것을 나타내는 어노테이션
public class CheckLoginAspect {

    @Before("@annotation(com.zudbs.project.annotation.CheckLogin)")
    public void checkLogin(JoinPoint  joinPoint) throws Throwable {

        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        User user= (User) session.getAttribute("user");

        if (user == null) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "NO_LOGIN") {};
        }
    }
}
