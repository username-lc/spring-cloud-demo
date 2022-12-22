package com.lc.cloud.oauth2.service.impl;

import com.lc.cloud.oauth2.service.ImageCodeService;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author LC
 * @created by LC on 2022/12/6
 * @description
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ImageCodeServiceImpl implements ImageCodeService {

    private static final Integer DEFAULT_IMAGE_WIDTH = 120;
    private static final Integer DEFAULT_IMAGE_HEIGHT = 40;

    private static final String IMAGE_CODE_KEY = "IMAGE_CODE:PAYMENT:RANDOM:";
    private static final long VALIDITY_TIME = 300;

    private final StringRedisTemplate redisTemplate;

    /**
     * 获取图形验证码
     *
     * @param randomStr 随机数
     * @param response  response
     * @throws IOException IOException
     */
    @Override
    public void getImageCode(String randomStr, HttpServletResponse response) throws IOException {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 算术类型  图片的宽高 计算位数
        ArithmeticCaptcha captcha =
                new ArithmeticCaptcha(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT, 2);
        // 获取运算的公式：3+2=?
        captcha.getArithmeticString();
        String code = captcha.text();
        // 验证码存入redis
        String key = IMAGE_CODE_KEY + randomStr;
        log.info("randomStr:{},code:{}", randomStr, code);
        redisTemplate.opsForValue().set(key, code, VALIDITY_TIME, TimeUnit.SECONDS);
        // 输出图片流
        captcha.out(response.getOutputStream());
    }

    @Override
    public boolean checkCode(String randomStr, String code) {
        String key = IMAGE_CODE_KEY + randomStr;
        log.info("checkCode,randomStr:{},code:{} ", randomStr, code);
        if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
            return false;
        }
        String redisCode = String.valueOf(redisTemplate.opsForValue().get(key));
        if (StringUtils.isEmpty(redisCode)) {
            return false;
        }
        if (code.equals(redisCode)) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }


}
