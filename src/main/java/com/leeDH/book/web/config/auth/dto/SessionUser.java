package com.leeDH.book.web.config.auth.dto;

import com.leeDH.book.web.domain.user.User;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.io.Serializable;

@Getter
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionUser implements Serializable {

    /* SessionUser에는 인증된 사용자 정보만 필요하다.
     * 그외에 필요한 정보들은 없으니 name,email,picutre만 필드로 선언 */
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
