package com.lc.cloud.oauth2.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cl.cloud.core.response.ResultResponse;
import com.lc.cloud.oauth2.Account;
import com.lc.cloud.oauth2.security.AccountUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author LC
 * @created by LC on 2022/12/5
 * @description
 */
@Component
@Slf4j
@RefreshScope
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String userServiceUrl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResultResponse resultResponse =
                restTemplate.getForObject(userServiceUrl + "/account/findByUsername?username={1}",
                        ResultResponse.class, username);

        Account account = JSONObject.parseObject(JSONObject.toJSONString(resultResponse.getData()), Account.class);
        //校验用户是否存在
        if (null == account) {
            log.error("用户不存在,username:{}", username);
            throw new UsernameNotFoundException("用户不存在");
        }
        return new AccountUser(account.getUsername(), account.getPassword(), account.getUid(), Collections.emptyList());
    }
}

