package com.lc.oauth2.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author LC
 * @created by LC on 2022/12/12 16:52
 * @description 存储账户信息 认证信息
 */
public class AccountUser extends User {
    private String uid;

    public String getUid() {
        return uid;
    }

    public AccountUser(String username, String password, String uid,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.uid = uid;
    }
}
