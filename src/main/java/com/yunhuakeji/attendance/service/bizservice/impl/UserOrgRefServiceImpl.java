package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.dao.bizdao.UserOrgRefMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.UserBuildingRef;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.service.bizservice.UserOrgRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
