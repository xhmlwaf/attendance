package com.yunhuakeji.attendance.service.bizservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.bizdao.CareMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.Care;
import com.yunhuakeji.attendance.service.bizservice.CareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class CareServiceImpl implements CareService {

  @Autowired
  private CareMapper careMapper;

  @Override
  public PageInfo page(long instructorId, Byte careStatus, int pageNo, int pageSize) {
    Example example = new Example(Care.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("instructorId", instructorId);
    criteria.andEqualTo("careStatus", careStatus);
    PageHelper.startPage(pageNo,pageSize);
    List<Care> careList = careMapper.selectByExample(example);
    PageInfo pageInfo = new PageInfo(careList);

    return pageInfo;
  }

  @Override
  public void update(Care care) {
    care.setDealTime(new Date());
    careMapper.updateByPrimaryKeySelective(care);
  }
}
