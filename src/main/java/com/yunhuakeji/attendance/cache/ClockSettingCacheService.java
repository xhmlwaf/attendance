package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.service.bizservice.ClockSettingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统配置缓存服务
 */
@Service
public class ClockSettingCacheService extends DataCacheService {

  @Autowired
  private ClockSettingService clockSettingService;

  @Override
  public List listAll() {
    return clockSettingService.listClockSetting();
  }

  @Override
  public long getPeriod() {
    return 1000 * 60 * 60;
  }

}
