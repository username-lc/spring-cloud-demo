package com.lc.cloud.oauth2.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author LC
 * @created by LC on 2022/12/21 11:43
 * @description
 */
@Configuration
public class WebConfig {
    /**
     * @return
     * @LoadBalanced这个注解是让 RestTemplate 开启负载均衡的能力
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
