package com.yunhuakeji.attendance.service.bizservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.bizdao.AccountMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dao.bizdao.model.AccountBaseInfoDO;
import com.yunhuakeji.attendance.dao.bizdao.model.Care;
import com.yunhuakeji.attendance.enums.RoleType;
import com.yunhuakeji.attendance.service.bizservice.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountMapper accountMapper;

  @Override
  public PageInfo<AccountBaseInfoDO> secondaryCollegeAdminPageQuery(String nameOrCode, int pageNo, int pageSize) {
    PageHelper.startPage(pageNo, pageSize);
    List<AccountBaseInfoDO> accountBaseInfoDOList = accountMapper.listAccountAdminByNameAndCode(nameOrCode, RoleType.SecondaryCollegeAdmin.getType());
    PageInfo pageInfo = new PageInfo(accountBaseInfoDOList);
    return pageInfo;
  }

  @Override
  public PageInfo<AccountBaseInfoDO> dormitoryAdminPageQuery(String nameOrCode, int pageNo, int pageSize) {
    PageHelper.startPage(pageNo, pageSize);
    List<AccountBaseInfoDO> accountBaseInfoDOList = accountMapper.listAccountAdminByNameAndCode(nameOrCode, RoleType.DormitoryAdmin.getType());
    PageInfo pageInfo = new PageInfo(accountBaseInfoDOList);
    return pageInfo;
  }

  @Override
  public PageInfo<AccountBaseInfoDO> studentOfficeAdminPageQuery(String nameOrCode, int pageNo, int pageSize) {
    PageHelper.startPage(pageNo, pageSize);
    List<AccountBaseInfoDO> accountBaseInfoDOList = accountMapper.listAccountAdminByNameAndCode(nameOrCode, RoleType.StudentsAffairsAdmin.getType());
    PageInfo pageInfo = new PageInfo(accountBaseInfoDOList);
    return pageInfo;
  }

  @Override
  public Account getAccountByUserId(Long userId) {
    Example example = new Example(Account.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("userId", userId);
    List<Account> accountList = accountMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(accountList)) {
      return null;
    } else {
      return accountList.get(0);
    }
  }

  @Override
  public void updateAccount(Account account) {
    accountMapper.updateByPrimaryKeySelective(account);
  }
}
