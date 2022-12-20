package com.lc.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.oauth2.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LC
 * @created by LC on 2022/12/12 13:34
 * @description
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
