package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.dao.bizdao.CheckDormitoryMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.CheckDormitory;
import com.yunhuakeji.attendance.service.bizservice.CheckDormitoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class CheckDormitoryServiceImpl implements CheckDormitoryService {

  @Autowired
  private CheckDormitoryMapper checkDormitoryMapper;

  @Override
  public List<CheckDormitory> list(List<Long> dormitoryIds, long statDate) {
    Example example = new Example(CheckDormitory.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIn("dormitoryId", dormitoryIds);
    criteria.andEqualTo("STAT_DATE", statDate);
    return checkDormitoryMapper.selectByExample(example);
  }

  @Override
  public void insert(CheckDormitory checkDormitory) {
    checkDormitoryMapper.insert(checkDormitory);
  }


}
