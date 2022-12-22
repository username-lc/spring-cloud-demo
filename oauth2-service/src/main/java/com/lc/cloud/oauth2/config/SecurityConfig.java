package com.lc.cloud.oauth2.config;


import com.lc.cloud.oauth2.security.*;
import com.lc.cloud.oauth2.service.ImageCodeService;
import com.lc.cloud.oauth2.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author LC
 * @created by LC on 2022/12/5
 * @description security核心配置类
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DefaultTokenServices defaultTokenServices;

    private final UserDetailsServiceImpl userDetailsService;

    private final ImageCodeService imageCodeService;

    private final ClientDetailsService clientDetailsService;

    private final Md5PasswordEncoder md5PasswordEncoder;

    /**
     * 注册一个认证管理器对象到容器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(accountLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AccountLoginAuthenticationFilter accountLoginAuthenticationFilter() throws Exception {
        AccountLoginAuthenticationFilter accountLoginAuthenticationFilter =
                new AccountLoginAuthenticationFilter();
        accountLoginAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        accountLoginAuthenticationFilter.setAuthenticationSuccessHandler(
                new AccountLoginAuthenticationSuccessHandler(defaultTokenServices, clientDetailsService));
        accountLoginAuthenticationFilter.setAuthenticationFailureHandler(
                new AccountLoginAuthenticationFailureHandler());
        return accountLoginAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new AccountLoginAuthenticationProvider(userDetailsService, md5PasswordEncoder,
                imageCodeService));
    }

}
