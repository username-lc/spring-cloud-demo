package com.lc.oauth2.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author LC
 * @created by LC on 2022/12/5
 * @description 账户登录token
 */
public class AccountLoginAuthenticationToken extends UsernamePasswordAuthenticationToken implements Serializable {

    private String randomStr;
    private String code;

    public AccountLoginAuthenticationToken(Object principal, Object credentials, String randomStr,
                                           String code) {
        super(principal, credentials);
        this.randomStr = randomStr;
        this.code = code;
    }

    public AccountLoginAuthenticationToken(Object principal, Object credentials, String randomStr,
                                           String code, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.randomStr = randomStr;
        this.code = code;
    }

    public String getRandomStr() {
        return randomStr;
    }

    public void setRandomStr(String randomStr) {
        this.randomStr = randomStr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
