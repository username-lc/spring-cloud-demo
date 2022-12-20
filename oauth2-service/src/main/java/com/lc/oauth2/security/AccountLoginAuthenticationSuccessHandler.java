package com.lc.oauth2.security;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.CharEncoding;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LC
 * @created by LC on 2022/12/5
 * @description 登录成功处理器
 */
@AllArgsConstructor
public class AccountLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final DefaultTokenServices defaultTokenServices;
    private final ClientDetailsService clientDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId("payment-client");
        TokenRequest tokenRequest = new TokenRequest(Collections.emptyMap(), clientDetails.getClientId(),
                clientDetails.getScope(),
                "password");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication =
                new OAuth2Authentication(oAuth2Request, authentication);

        OAuth2AccessToken accessToken =
                defaultTokenServices.createAccessToken(oAuth2Authentication);
        AccountUser accountUser = (AccountUser) authentication.getPrincipal();
        Map<String, Object> result = new HashMap<>();
        result.put("access_token", accessToken.getValue());
        result.put("user_id", accountUser.getUid());
        result.put("username", accountUser.getUsername());
        response.setCharacterEncoding(CharEncoding.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().println(JSON.toJSONString(result));
    }
}
