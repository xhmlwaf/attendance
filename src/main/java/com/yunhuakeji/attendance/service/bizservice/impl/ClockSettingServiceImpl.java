package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.dao.bizdao.ClockAddressSettingMapper;
import com.yunhuakeji.attendance.dao.bizdao.ClockDaySettingMapper;
import com.yunhuakeji.attendance.dao.bizdao.ClockSettingMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockAddressSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.bizservice.ClockAddressSettingService;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.service.bizservice.ClockSettingService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

import tk.mybatis.mapper.entity.Example;

@Service
public class ClockSettingServiceImpl implements ClockSettingService {

  @Autowired
  private ClockSettingMapper clockSettingMapper;

  @Autowired
  private ClockAddressSettingMapper clockAddressSettingMapper;

  @Autowired
  private ClockDaySettingMapper clockDaySettingMapper;


  @Override
  public List<ClockSetting> listClockSetting() {
    return clockSettingMapper.selectAll();
  }

  @Override
  public void update(ClockSetting clockSetting) {
    clockSettingMapper.updateByPrimaryKeySelective(clockSetting);
  }

  @Override
  public ClockSetting getClockSetting() {
    //获取统计日期，根据配置的查寝时间来算
    List<ClockSetting> clockSettingList = clockSettingMapper.selectAll();
    if (CollectionUtils.isEmpty(clockSettingList)) {
      throw new BusinessException(ErrorCode.CHECK_TIME_NOT_CONFIG);
    }
    return clockSettingList.get(0);
  }

  @Override
  @Transactional
  public void updateConfig(ClockSetting clockSetting,
                           List<ClockAddressSetting> clockAddressSettingList,
                           Set<ClockDaySetting> clockDaySettingList) {

    Example clockSettingExample = new Example(ClockSetting.class);
    clockSettingMapper.deleteByExample(clockSettingExample);
    clockSettingMapper.insertSelective(clockSetting);

    Example clockAddressSettingExample = new Example(ClockAddressSetting.class);
    clockAddressSettingMapper.deleteByExample(clockAddressSettingExample);
    if (!CollectionUtils.isEmpty(clockAddressSettingList)) {
      for (ClockAddressSetting clockAddressSetting : clockAddressSettingList) {
        clockAddressSettingMapper.insertSelective(clockAddressSetting);
      }
    }

    Example clockDaySettingExample = new Example(ClockDaySetting.class);
    Example.Criteria criteria = clockDaySettingExample.createCriteria();
    criteria.andGreaterThanOrEqualTo("yearMonth", DateUtil.getCurrYearMonth());
    clockDaySettingMapper.deleteByExample(clockDaySettingExample);
    if (!CollectionUtils.isEmpty(clockDaySettingList)) {
      for (ClockDaySetting clockDaySetting : clockDaySettingList) {
        clockDaySettingMapper.insertSelective(clockDaySetting);
      }

    }

  }
}
