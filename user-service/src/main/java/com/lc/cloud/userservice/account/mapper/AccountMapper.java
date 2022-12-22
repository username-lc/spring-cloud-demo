package com.lc.cloud.userservice.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.cloud.userservice.account.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LC
 * @created by LC on 2022/12/12 13:34
 * @description
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
