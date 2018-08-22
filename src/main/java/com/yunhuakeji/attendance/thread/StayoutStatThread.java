package com.yunhuakeji.attendance.thread;

import com.yunhuakeji.attendance.cache.ClockDaySettingCacheService;
import com.yunhuakeji.attendance.cache.ClockSettingCacheService;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.impl.StudentClockServiceImpl;
import com.yunhuakeji.attendance.util.ApplicationUtils;
import com.yunhuakeji.attendance.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 自动转未归线程
 */
public class StayoutStatThread implements Runnable {

  public static final Logger logger = LoggerFactory.getLogger(StayoutStatThread.class);

  @Override
  public void run() {

    ClockDaySettingCacheService clockDaySettingCacheService = ApplicationUtils.getBean(ClockDaySettingCacheService.class);
    ClockSettingCacheService clockSettingCacheService = ApplicationUtils.getBean(ClockSettingCacheService.class);
    StudentClockService studentClockService = ApplicationUtils.getBean(StudentClockServiceImpl.class);

    while (true) {
      try {
        Thread.sleep(1000);
        //校验今天是否需要打卡
        List<Integer> allDayList = clockDaySettingCacheService.list();
        if (allDayList == null || allDayList.contains(DateUtil.getCurrDay())) {
          continue;
        }

        List<ClockSetting> clockSettingList = clockSettingCacheService.list();
        if (CollectionUtils.isEmpty(clockSettingList)) {
          continue;
        }
        ClockSetting clockSetting = clockSettingList.get(0);



      } catch (InterruptedException e) {
        logger.error("", e);
      }
    }


  }
}
