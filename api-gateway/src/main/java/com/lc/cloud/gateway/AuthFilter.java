package com.lc.cloud.gateway;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author LC
 * @created by LC on 2022/12/23 9:36
 * @description
 */
@Component
@RequiredArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {

    private final StringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String path = request.getURI().getPath();
        if (path.equals("/auth/login") || path.equals("/auth/code")) {
            return chain.filter(exchange);
        }

        String token = getToken(request);

        if (StringUtils.isBlank(token)) {
            // token为空 返回401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        String s = redisTemplate.opsForValue().get("AUTH-TOKEN:PAYMENT:access:" + token);
        if (StringUtils.isBlank(s)) {
            // token为空 返回401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 完成验证
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    private static String getToken(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        // 从请求头获取token
        String token = headers.getFirst("Authorization");
        if (StringUtils.isNotBlank(token)) {
            token = token.split(" ")[1];
        } else {
            // 请求头无token则从url获取token
            token = request.getQueryParams().getFirst("token");
        }
        return token;
    }
}
