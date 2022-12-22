package com.lc.cloud.oauth2.controller;

import com.lc.cloud.oauth2.service.ImageCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LC
 * @created by LC on 2022/12/6
 * @description 图形验证码
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/code")
public class ImageCodeController {

    private final ImageCodeService imageCodeService;

    @GetMapping()
    public void getImageCode(@RequestParam("randomStr") String randomStr,
                             HttpServletResponse response) throws IOException {
        imageCodeService.getImageCode(randomStr, response);
    }
}
