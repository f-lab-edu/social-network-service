package com.zudbs.project.annotation;

import com.zudbs.project.util.SessionKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionVariable {

    public SessionKey value() default SessionKey.NONE;
}