package com.lc.oauth2.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LC
 * @created by LC on 2022/12/12 16:05
 * @description
 */
public interface ImageCodeService {

    /**
     * 获取图形验证码
     *
     * @param randomStr 随机数
     * @param response
     * @throws IOException
     */
    void getImageCode(String randomStr, HttpServletResponse response) throws IOException;

    /**
     * 验证验证码
     *
     * @param randomStr
     * @param code
     * @return
     */
    boolean checkCode(String randomStr, String code);
}
