package com.yunhuakeji.attendance.service.bizservice;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dao.bizdao.model.AccountBaseInfoDO;

import java.util.List;

public interface AccountService {

  PageInfo<AccountBaseInfoDO> secondaryCollegeAdminPageQuery(String nameOrCode, int pageNo, int pageSize);

  PageInfo<AccountBaseInfoDO> dormitoryAdminPageQuery(String nameOrCode, int pageNo, int pageSize);

  PageInfo<AccountBaseInfoDO> studentOfficeAdminPageQuery(String nameOrCode, int pageNo, int pageSize);

  Account getAccountByUserId(Long userId);

  List<Account> getByRoleType(byte roleType);

  void updateAccount(Account account);

  void delete(byte roleType,List<Long> userIds);

  void batchInsert(List<Account> accountList);
}
