package com.lc.cloud.userservice.account.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.cloud.userservice.account.dto.request.AccountRequest;
import com.lc.cloud.userservice.account.dto.response.AccountResponse;
import com.lc.cloud.userservice.account.entity.Account;

/**
 * @author LC
 * @created by LC on 2022/12/12 13:33
 * @description
 */
public interface AccountService extends IService<Account> {
    /**
     * 新增登录账户
     *
     * @param accountRequest 账户信息
     * @return boolean
     */
    boolean createAccount(AccountRequest accountRequest);

    /**
     * 修改登录账户
     *
     * @param accountRequest 账户信息
     * @return boolean
     */
    boolean modifyAccount(AccountRequest accountRequest);

    /**
     * 删除登录账户
     *
     * @param username 用户名
     * @return boolean
     */
    boolean removeAccount(String username);

    /**
     * 登录账户分页列表
     * @param page 分页
     * @param accountRequest 查询条件
     * @return Page<AccountResponse>
     */
    Page<AccountResponse> findAccountPageByCondition(Page page, AccountRequest accountRequest);


    /**
     * 通过username查询账户
     * @param username
     * @return
     */
    Account findByUsername(String username);
}
