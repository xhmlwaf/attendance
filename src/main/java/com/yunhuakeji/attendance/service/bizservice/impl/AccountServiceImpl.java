package com.yunhuakeji.attendance.service.bizservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.bizdao.AccountMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.AccountBaseInfoDO;
import com.yunhuakeji.attendance.enums.RoleType;
import com.yunhuakeji.attendance.service.bizservice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public PageInfo<AccountBaseInfoDO> secondaryCollegeAdminPageQuery(String name, String code, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<AccountBaseInfoDO> accountBaseInfoDOList = accountMapper.listAccountAdminByNameAndCode(name, code, RoleType.SecondaryCollegeAdmin.getType());
        PageInfo pageInfo = new PageInfo(accountBaseInfoDOList);
        return pageInfo;
    }

    @Override
    public PageInfo<AccountBaseInfoDO> dormitoryAdminPageQuery(String name, String code, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<AccountBaseInfoDO> accountBaseInfoDOList = accountMapper.listAccountAdminByNameAndCode(name, code, RoleType.DormitoryAdmin.getType());
        PageInfo pageInfo = new PageInfo(accountBaseInfoDOList);
        return pageInfo;
    }

    @Override
    public PageInfo<AccountBaseInfoDO> studentOfficeAdminPageQuery(String name, String code, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<AccountBaseInfoDO> accountBaseInfoDOList = accountMapper.listAccountAdminByNameAndCode(name, code, RoleType.StudentsAffairsAdmin.getType());
        PageInfo pageInfo = new PageInfo(accountBaseInfoDOList);
        return pageInfo;
    }
}
