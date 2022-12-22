package com.lc.cloud.userservice.account.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cl.cloud.core.response.ResultResponse;
import com.lc.cloud.userservice.account.dto.request.AccountRequest;
import com.lc.cloud.userservice.account.dto.response.AccountResponse;
import com.lc.cloud.userservice.account.entity.Account;
import com.lc.cloud.userservice.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author LC
 * @created by LC on 2022/12/12 13:32
 * @description
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    /**
     * 新增登录账户
     *
     * @param accountRequest 账户信息
     * @return ResultResponse
     */
    @PostMapping()
    public ResultResponse postAccount(@RequestBody AccountRequest accountRequest) {
        boolean result = accountService.createAccount(accountRequest);
        return result ? ResultResponse.ok(true, "新增成功") : ResultResponse.failed(false, "保存失败,登录账户已存在");
    }

    /**
     * 修改登录账户
     *
     * @param accountRequest 账户信息
     * @return ResultResponse
     */
    @PutMapping()
    public ResultResponse putAccount(@RequestBody AccountRequest accountRequest) {
        boolean result = accountService.modifyAccount(accountRequest);
        return result ? ResultResponse.ok(true, "修改成功") : ResultResponse.failed(false, "修改失败");
    }

    /**
     * 删除登录账户
     *
     * @param username 用户名
     * @return ResultResponse
     */
    @DeleteMapping()
    public ResultResponse deleteAccount(@RequestParam String username) {
        boolean result = accountService.removeAccount(username);
        return result ? ResultResponse.ok(true, "删除成功") : ResultResponse.failed(false, "删除失败");
    }

    /**
     * 登录账户分页列表
     *
     * @param page           分页
     * @param accountRequest 查询条件
     * @return ResultResponse
     */
    @GetMapping
    public ResultResponse getAccountPage(Page page, AccountRequest accountRequest) {
        Page<AccountResponse> accountResponsePage = accountService.findAccountPageByCondition(page, accountRequest);
        return ResultResponse.ok(accountResponsePage, "获取数据成功");
    }

    @GetMapping("/findByUsername")
    public ResultResponse getAccountByUsername(String username) {
        Account account = accountService.findByUsername(username);
        return ResultResponse.ok(account, "获取数据成功");
    }
}
