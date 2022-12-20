package com.lc.oauth2.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author LC
 * @created by LC on 2022/12/5
 * @description
 */
@Configuration
@AllArgsConstructor
public class RedisTokenStoreConfiguration {

    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        //设置redis中token存储中的前缀
        redisTokenStore.setPrefix("AUTH-TOKEN:PAYMENT:");
        return redisTokenStore;
    }

}
