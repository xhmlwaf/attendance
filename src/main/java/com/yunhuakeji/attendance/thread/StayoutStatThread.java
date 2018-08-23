package com.yunhuakeji.attendance.thread;

import com.yunhuakeji.attendance.cache.ClockDaySettingCacheService;
import com.yunhuakeji.attendance.cache.ClockSettingCacheService;
import com.yunhuakeji.attendance.cache.StudentClockCache;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.impl.StudentClockServiceImpl;
import com.yunhuakeji.attendance.util.ApplicationUtils;
import com.yunhuakeji.attendance.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 自动转未归线程
 */
public class StayoutStatThread implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(StayoutStatThread.class);

  private static final HashMap<Long, Object> statMap = new HashMap();

  @Override
  public void run() {

    ClockDaySettingCacheService clockDaySettingCacheService = ApplicationUtils.getBean(ClockDaySettingCacheService.class);
    ClockSettingCacheService clockSettingCacheService = ApplicationUtils.getBean(ClockSettingCacheService.class);
    StudentClockService studentClockService = ApplicationUtils.getBean(StudentClockServiceImpl.class);

    while (true) {
      try {
        Long currDate = DateUtil.currYYYYMMddToLong();
        Thread.sleep(5000);
        //校验今天是否需要打卡
        List<Integer> allDayList = clockDaySettingCacheService.list();
        if (allDayList == null || allDayList.contains(DateUtil.getCurrDay())) {
          continue;
        }

        //是否配置了打卡时间
        List<ClockSetting> clockSettingList = clockSettingCacheService.list();
        if (CollectionUtils.isEmpty(clockSettingList)) {
          continue;
        }
        //获取打卡结束时间和当前时间比较
        ClockSetting clockSetting = clockSettingList.get(0);
        long clockEndTime = clockSetting.getClockEndTime();
        long currTime = DateUtil.currHhmmssToLong();
        logger.info("currTime:{},clockEndTime:{}", currTime, clockEndTime);
        if (currTime >= clockEndTime && statMap.get(currDate) == null) {
          List<Long> studentIds = studentClockService.getNotClockStudentIds(currDate);
          if (!CollectionUtils.isEmpty(studentIds)) {
            for (Long studentId : studentIds) {
              StudentClock studentClock = new StudentClock();
              studentClock.setUserId(studentId);
              studentClock.setClockStatus(ClockStatus.STAYOUT.getType());
              StudentClockCache.put(studentClock);
            }
          }
          statMap.clear();
          statMap.put(currDate, this);
        }

      } catch (InterruptedException e) {
        logger.error("", e);
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(DateUtil.currHhmmssToLong());
  }
}
