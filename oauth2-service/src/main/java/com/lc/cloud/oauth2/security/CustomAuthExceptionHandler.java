package com.lc.cloud.oauth2.security;

import com.alibaba.fastjson.JSON;
import com.cl.cloud.core.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LC
 * @created by LC on 2022/12/6
 * @description 认证异常处理器 例如token失效
 */
@Slf4j
public class CustomAuthExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        Throwable cause = authException.getCause();
        response.setCharacterEncoding(CharEncoding.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResultResponse<Object> resultResponse = ResultResponse.failed();
        if (cause instanceof InvalidTokenException) {
            log.error("InvalidTokenException : {}", cause.getMessage());
            //Token无效
            resultResponse.setMsg("Token无效,请重新登录");
            response.getWriter().write(JSON.toJSONString(resultResponse));
        } else {
            log.error("AuthenticationException : NoAuthentication");
            resultResponse.setMsg("未授权,请登录");
            response.getWriter().write(JSON.toJSONString(resultResponse));
        }
    }

}
