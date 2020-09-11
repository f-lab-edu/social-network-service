package com.zudbs.project.aop;

import com.zudbs.project.annotation.SessionVariable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
public class SessionVariableAspect {

    @Autowired
    private HttpSession session;


    public Object setSessionVariable(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            SessionVariable sessionVariable = parameter.getAnnotation(SessionVariable.class);

            if (sessionVariable != null) {
                args[i] = session.getAttribute(sessionVariable.value());
            }
        }

        return joinPoint.proceed(args);
    }
}
