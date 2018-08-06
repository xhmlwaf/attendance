package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.dao.bizdao.UserBuildingRefMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.dao.bizdao.model.UserBuildingRef;
import com.yunhuakeji.attendance.service.bizservice.UserBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserBuildingServiceImpl implements UserBuildingService {

    @Autowired
    private UserBuildingRefMapper userBuildingRefMapper;

    @Override
    public List<UserBuildingRef> listByUserId(Long userId) {
        Example example = new Example(UserBuildingRef.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        return userBuildingRefMapper.selectByExample(example);
    }
}
