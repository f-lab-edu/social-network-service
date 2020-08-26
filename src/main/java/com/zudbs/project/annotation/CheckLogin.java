package com.zudbs.project.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //애노테이션을 메소드에 사용
@Retention(RetentionPolicy.RUNTIME)
/* RetentionPolicy
RUNTIME : 컴파일 이후에도 JVM에 의해서 참조가 가능합니다.
CLASS   : 컴파일러가 클래스를 참조할 때까지 유효합니다.
SOURCE  : 어노테이션 정보는 컴파일 이후 없어집니다. */

public @interface CheckLogin {
}