package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AccountMapper extends Mapper<Account> {
    int insertBatchSelective(List<Account> records);
}