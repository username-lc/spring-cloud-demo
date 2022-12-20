package com.lc.oauth2.service.impl;


import com.lc.oauth2.entity.Account;
import com.lc.oauth2.security.AccountUser;
import com.lc.oauth2.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author LC
 * @created by LC on 2022/12/5
 * @description
 */
@AllArgsConstructor
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);
        //校验用户是否存在
        if (null == account) {
            log.error("用户不存在,username:{}", username);
            throw new UsernameNotFoundException("用户不存在");
        }
        return new AccountUser(account.getUsername(), account.getPassword(), account.getUid(), Collections.emptyList());
    }
}
