package com.lc.oauth2;

import org.springframework.security.core.AuthenticationException;

/**
 * @author LC
 * @created by LC on 2022/12/5
 * @description 登录异常
 */
public class LoginException extends AuthenticationException {

    public String msg;

    public LoginException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
