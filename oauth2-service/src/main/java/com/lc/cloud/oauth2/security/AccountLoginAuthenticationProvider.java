package com.lc.cloud.oauth2.security;

import com.lc.cloud.oauth2.service.ImageCodeService;
import com.lc.cloud.oauth2.LoginException;
import com.lc.cloud.oauth2.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author LC
 * @date 2022/12/2
 * @description 账户登录处理逻辑
 */
@Slf4j
public class AccountLoginAuthenticationProvider extends DaoAuthenticationProvider {

    private final ImageCodeService imageCodeService;

    public AccountLoginAuthenticationProvider(UserDetailsServiceImpl userDetailsService,
                                              Md5PasswordEncoder md5PasswordEncoder,
                                              ImageCodeService imageCodeService) {
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(md5PasswordEncoder);
        this.imageCodeService = imageCodeService;
    }


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //验证验证码
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
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
