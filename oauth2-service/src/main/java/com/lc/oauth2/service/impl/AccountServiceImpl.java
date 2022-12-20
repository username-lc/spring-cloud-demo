package com.lc.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.oauth2.entity.Account;
import com.lc.oauth2.mapper.AccountMapper;
import com.lc.oauth2.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author LC
 * @created by LC on 2022/12/12 13:33
 * @description
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {


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
