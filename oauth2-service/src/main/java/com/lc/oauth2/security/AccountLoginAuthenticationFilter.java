package com.lc.oauth2.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LC
 * @created by LC on 2022/12/5
 * @description 账户登录过滤器
 */
public class AccountLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private static final String SPRING_SECURITY_FORM_RANDOM_STR_KEY = "randomStr";
    private static final String SPRING_SECURITY_FORM_CODE_KEY = "code";

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException {
        String username = obtainUsername(httpServletRequest);
        String password = obtainPassword(httpServletRequest);
        String randomStr = httpServletRequest.getParameter(SPRING_SECURITY_FORM_RANDOM_STR_KEY);
        String code = httpServletRequest.getParameter(SPRING_SECURITY_FORM_CODE_KEY);

        AccountLoginAuthenticationToken accountLoginAuthenticationToken =
                new AccountLoginAuthenticationToken(username, password, randomStr, code);

        // Allow subclasses to set the "details" property
        setDetails(httpServletRequest, accountLoginAuthenticationToken);
        return this.getAuthenticationManager().authenticate(accountLoginAuthenticationToken);
    }
}

