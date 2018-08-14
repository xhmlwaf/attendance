package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.dao.bizdao.TermConfigMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentDeviceRef;
import com.yunhuakeji.attendance.dao.bizdao.model.TermConfig;
import com.yunhuakeji.attendance.service.bizservice.TermConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class TermConfigServiceImpl implements TermConfigService {

  @Autowired
  private TermConfigMapper termConfigMapper;

  @Override
  public TermConfig getCurrTermConfig() {
    Example example = new Example(TermConfig.class);
    Example.Criteria criteria = example.createCriteria();
    Date d = new Date();
    criteria.andLessThanOrEqualTo("startDate", d);
    criteria.andGreaterThanOrEqualTo("endDate", d);
    List<TermConfig> termConfigList = termConfigMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(termConfigList)) {
      return null;
    }
    return termConfigList.get(0);
  }
}
