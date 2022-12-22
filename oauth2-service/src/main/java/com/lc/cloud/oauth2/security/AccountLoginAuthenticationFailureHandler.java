package com.lc.cloud.oauth2.security;

import com.alibaba.fastjson.JSON;
import com.cl.cloud.core.response.ResultResponse;
import org.apache.commons.codec.CharEncoding;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LC
 * @created by LC on 2022/12/5
 * @description 登录异常处理器
 */
public class AccountLoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        ResultResponse<Object> resultResponse = ResultResponse.failed(exception.getMessage());
        response.setCharacterEncoding(CharEncoding.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println(JSON.toJSONString(resultResponse));
    }
}
