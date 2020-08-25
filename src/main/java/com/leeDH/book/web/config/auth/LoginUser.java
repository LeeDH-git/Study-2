package com.leeDH.book.web.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 이 어노테이션이 생성될 위치를 지정,Parameter로 지정 했으니 파라미터로 선언된 객체에서만 사용 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    // @interface : 이 파일을 어노테이션 클래스로 지정 , LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면 된다.
}
