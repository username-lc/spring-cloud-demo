package com.lc.cloud.userservice.account.dto.request;

import lombok.Data;


/**
 * @author LC
 * @created by LC on 2022/12/12 13:36
 * @description
 */
@Data
public class AccountRequest {
    /**
     * 登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

}
