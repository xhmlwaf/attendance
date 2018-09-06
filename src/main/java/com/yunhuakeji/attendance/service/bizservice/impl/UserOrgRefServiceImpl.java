package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.dao.bizdao.UserOrgRefMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.service.bizservice.UserOrgRefService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserOrgRefServiceImpl implements UserOrgRefService {

  @Autowired
  private UserOrgRefMapper userOrgRefMapper;

  @Override
  public List<UserOrgRef> listByUserIds(List<Long> userIds) {
    Example example = new Example(UserOrgRef.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIn("userId", userIds);
    return userOrgRefMapper.selectByExample(example);
  }

  @Override
  public List<UserOrgRef> listByUserId(Long userId) {
    Example example = new Example(UserOrgRef.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("userId", userId);
    return userOrgRefMapper.selectByExample(example);
  }

  @Override
  @Transactional
  public void batchInsert(List<Long> userIds, List<UserOrgRef> userOrgRefList) {
    Example example = new Example(UserOrgRef.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIn("userId", userIds);
    userOrgRefMapper.deleteByExample(example);

    if (!CollectionUtils.isEmpty(userOrgRefList)) {
      for (UserOrgRef ref : userOrgRefList) {
        userOrgRefMapper.insertSelective(ref);
      }
    }
  }

  @Override
  public void deleteByUserIds(List<Long> userIds) {
    Example example = new Example(UserOrgRef.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIn("userId", userIds);
    userOrgRefMapper.deleteByExample(example);
  }
}
