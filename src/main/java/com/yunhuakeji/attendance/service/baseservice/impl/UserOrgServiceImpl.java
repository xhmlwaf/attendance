package com.yunhuakeji.attendance.service.baseservice.impl;

import com.yunhuakeji.attendance.dao.basedao.UserOrgMapper;
import com.yunhuakeji.attendance.dao.basedao.model.UserOrg;
import com.yunhuakeji.attendance.service.baseservice.UserOrgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserOrgServiceImpl implements UserOrgService {

    @Autowired
    private UserOrgMapper userOrgMapper;

    @Override
    public int insert(UserOrg record) {
        return userOrgMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return userOrgMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserOrg record) {
        return userOrgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserOrg selectByPrimaryKey(String id) {
        return userOrgMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserOrg> selectByPrimaryKeyList(List<String> ids) {
        Example example = new Example(UserOrg.class);
        example.createCriteria().andIn("id",ids);
        return userOrgMapper.selectByExample(example);
    }

 }
