package com.lc.oauth2.security;

import com.lc.oauth2.LoginException;
import com.lc.oauth2.service.ImageCodeService;
import com.lc.oauth2.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Collections;

/**
 * @author LC
 * @created by LC on 2022/12/20 10:54
 * @description 自定义登录身份验证
 */
@Slf4j
@RequiredArgsConstructor
public class AccountLoginAuthenticationProvider1 implements AuthenticationProvider {
    private final UserDetailsServiceImpl userDetailsService;
    private final Md5PasswordEncoder md5PasswordEncoder;
    private final ImageCodeService imageCodeService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AccountLoginAuthenticationToken accountLoginAuthenticationToken =
                (AccountLoginAuthenticationToken) authentication;
        String randomStr = accountLoginAuthenticationToken.getRandomStr();
        String code = accountLoginAuthenticationToken.getCode();
        //验证验证码
        boolean checkCode = imageCodeService.checkCode(randomStr, code);
        if (!checkCode) {
            log.error("验证码错误,randomStr:{},code:{}", randomStr, code);
            throw new LoginException("验证码错误");
        }
        String username = accountLoginAuthenticationToken.getPrincipal().toString();
        String presentedPassword = authentication.getCredentials().toString();
        AccountUser accountUser = (AccountUser) userDetailsService.loadUserByUsername(username);
        if (!md5PasswordEncoder.matches(presentedPassword, accountUser.getPassword())) {
            throw new LoginException("密码错误");
        }

        return new AccountLoginAuthenticationToken(
                accountUser, authentication.getCredentials(), randomStr, code, Collections.emptyList());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return AccountLoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
