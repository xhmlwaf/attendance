package com.yunhuakeji.attendance.service.baseservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
  public List<UserOrg> selectByUserIds(List<Long> userIds) {
    Example example = new Example(UserOrg.class);
    example.createCriteria().andIn("userId", userIds);
    return userOrgMapper.selectByExample(example);
  }

  @Override
  public List<UserOrg> selectByUserId(Long userId) {
    Example example = new Example(UserOrg.class);
    example.createCriteria().andEqualTo("userId", userId);
    return userOrgMapper.selectByExample(example);
  }

  @Override
  public List<UserOrg> selectByOrgId(Long orgId) {
    Example example = new Example(UserOrg.class);
    example.createCriteria().andEqualTo("orgId", orgId);
    return userOrgMapper.selectByExample(example);
  }

  @Override
  public PageInfo<UserOrg> selectByOrgIdForPage(Long orgId, Integer pageNo, Integer pageSize) {
    Example example = new Example(UserOrg.class);
    example.createCriteria().andEqualTo("orgId", orgId);
    PageHelper.startPage(pageNo, pageSize);
    List<UserOrg> userOrgList = userOrgMapper.selectByExample(example);
    PageInfo pageInfo = new PageInfo(userOrgList);
    return pageInfo;
  }

}
