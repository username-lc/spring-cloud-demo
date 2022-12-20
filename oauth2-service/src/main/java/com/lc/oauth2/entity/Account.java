package com.lc.oauth2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LC
 * @created by LC on 2022/12/12 13:28
 * @description 用户登录账号表
 */
@Data
@TableName("chpay_account_sys")
public class Account {

    /**
     * 自增主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 修改时间
     */
    private LocalDateTime updateAt;

    /**
     * 逻辑删除
     */
    @TableLogic(delval = "id")
    private Integer deleteFlag;
}
