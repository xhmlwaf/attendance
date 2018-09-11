package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClockDaySettingCacheService extends DataCacheService {

  @Autowired
  private ClockDaySettingService clockDaySettingService;

  @Override
  public List listAll() {
    List<ClockDaySetting> clockDaySettingList = clockDaySettingService.listAll();
    if (!CollectionUtils.isEmpty(clockDaySettingList)) {
      return clockDaySettingList.stream().map(e -> {
        int yearMonthDay = (int) DateUtil.ymdTolong(e.getYearMonth(), e.getDay());
        return yearMonthDay;
      }).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  @Override
  public long getPeriod() {
    return 1000 * 60 * 60 * 24;
  }

  public static void main(String[] args) {

  }
}
