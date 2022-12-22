package com.lc.cloud.userservice.account.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LC
 * @created by LC on 2022/12/12 15:32
 * @description
 */
@Data
public class AccountResponse {

    /**
     * 自增主键id
     */

    private Integer id;

    /**
     * 账号uid,以sys_开头
     */
    private String uid;

    /**
     * 登录名
     */
    private String username;


    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;


}
