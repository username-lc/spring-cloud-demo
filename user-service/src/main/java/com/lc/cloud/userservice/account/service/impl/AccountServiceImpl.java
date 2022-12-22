package com.lc.cloud.userservice.account.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.cloud.userservice.account.dto.request.AccountRequest;
import com.lc.cloud.userservice.account.dto.response.AccountResponse;
import com.lc.cloud.userservice.account.entity.Account;
import com.lc.cloud.userservice.account.mapper.AccountMapper;
import com.lc.cloud.userservice.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author LC
 * @created by LC on 2022/12/12 13:33
 * @description
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public boolean createAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setUid("sys_" + IdUtil.simpleUUID());
        account.setUsername(accountRequest.getUsername());
        account.setPassword(SecureUtil.md5(accountRequest.getPassword()));
        account.setPhone(accountRequest.getPhone());
        try {
            this.save(account);
        } catch (DataAccessException e) {
            log.error("save account error,{}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean modifyAccount(AccountRequest accountRequest) {

        LambdaUpdateWrapper<Account> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Account::getUsername, accountRequest.getUsername());
        updateWrapper.set(StringUtils.hasText(accountRequest.getPassword()), Account::getPassword,
                SecureUtil.md5(accountRequest.getPassword()));
        updateWrapper.set(StringUtils.hasText(accountRequest.getPhone()), Account::getPhone,
                accountRequest.getPhone());
        return this.update(updateWrapper);
    }

    @Override
    public boolean removeAccount(String username) {
        return this.remove(new LambdaQueryWrapper<Account>().eq(Account::getUsername, username));
    }

    @Override
    public Page<AccountResponse> findAccountPageByCondition(Page page, AccountRequest accountRequest) {
        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<Account>()
                .eq(StringUtils.hasText(accountRequest.getPhone()), Account::getPhone, accountRequest.getPhone())
                .like(StringUtils.hasText(accountRequest.getUsername()), Account::getUsername,
                        accountRequest.getUsername());
        Page accountPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);
        accountPage.convert(account -> {
            AccountResponse accountResponse = new AccountResponse();
            BeanUtils.copyProperties(account, accountResponse);
            return accountResponse;
        });
        return accountPage;
    }

    @Override
    public Account findByUsername(String username) {
        LambdaQueryWrapper<Account> accountWrapper = new LambdaQueryWrapper<>();
        accountWrapper.eq(Account::getUsername, username);
        accountWrapper.last("limit 1");
        Account account = this.baseMapper.selectOne(accountWrapper);
        if (account == null) {
            log.debug("用户未找到:{}", username);
            return null;
        }
        return account;
    }
}
