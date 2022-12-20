package com.lc.oauth2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.oauth2.entity.Account;

/**
 * @author LC
 * @created by LC on 2022/12/12 13:33
 * @description
 */
public interface AccountService extends IService<Account> {


    /**
     * 通过username查询账户
     * @param username
     * @return
     */
    Account findByUsername(String username);
}
