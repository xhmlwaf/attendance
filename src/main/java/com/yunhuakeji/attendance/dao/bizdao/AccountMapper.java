package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dao.bizdao.model.AccountBaseInfoDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AccountMapper extends Mapper<Account> {
    int insertBatchSelective(List<Account> records);

    List<AccountBaseInfoDO> listAccountAdminByNameAndCode(@Param("nameOrCode") String nameOrCode, @Param("roleType") byte roleType);


}