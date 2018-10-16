package com.yunhuakeji.attendance.thread;

import com.yunhuakeji.attendance.cache.ClockDaySettingCacheService;
import com.yunhuakeji.attendance.cache.ClockSettingCacheService;
import com.yunhuakeji.attendance.cache.StudentClockCache;
import com.yunhuakeji.attendance.constants.ConfigConstants;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockDTO;
import com.yunhuakeji.attendance.enums.AppName;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.service.bizservice.RedisService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.impl.StudentClockServiceImpl;
import com.yunhuakeji.attendance.util.ApplicationUtils;
import com.yunhuakeji.attendance.util.DateUtil;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class StayoutStatThread implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(StayoutStatThread.class);

  private static final HashMap<Integer, Object> statMap = new HashMap();

  private static final String REDIS_CLOCK_DAY_PREFIX = "CLOCK_DAY_";

  private static final int PERIOD = 5 * 1000;

  private static final String RUNNING_FLAG_VALUE = "iamrunning";

  @Override
  public void run() {

    ClockDaySettingCacheService clockDaySettingCacheService = ApplicationUtils
        .getBean(ClockDaySettingCacheService.class);
    ClockSettingCacheService clockSettingCacheService = ApplicationUtils
        .getBean(ClockSettingCacheService.class);
    StudentClockService studentClockService = ApplicationUtils
        .getBean(StudentClockServiceImpl.class);
    RedisService redisService = ApplicationUtils.getBean(RedisService.class);

    while (true) {
      try {
        Integer currDate = (int) DateUtil.currYYYYMMddToLong();
        Thread.sleep(PERIOD);
        List<Integer> allDayList = clockDaySettingCacheService.list();
        if (allDayList != null && allDayList.contains(currDate)) {
        } else {
          continue;
        }
        List<ClockSetting> clockSettingList = clockSettingCacheService.list();
        if (CollectionUtils.isEmpty(clockSettingList)) {
          continue;
        }
        ClockSetting clockSetting = clockSettingList.get(0);
        long clockEndTime = clockSetting.getClockEndTime();
        long currTime = DateUtil.currHhmmssToLong();
        if (currTime >= clockEndTime && statMap.get(currDate) == null) {
          String result = redisService
              .getAndSet(REDIS_CLOCK_DAY_PREFIX + currDate, RUNNING_FLAG_VALUE);
          redisService.expire(REDIS_CLOCK_DAY_PREFIX + currDate, 60, TimeUnit.SECONDS);
          if (result == null) {
            List<Long> studentIds = studentClockService.getNotClockStudentIds(currDate);
            if (!CollectionUtils.isEmpty(studentIds)) {
              for (Long studentId : studentIds) {
                StudentClockDTO studentClock = new StudentClockDTO();
                studentClock.setUserId(studentId);
                studentClock.setClockStatus(ClockStatus.STAYOUT.getType());
                studentClock.setOperatorName(ConfigConstants.DEFAULT_SYSTEM_OPERATOR_NAME);
                studentClock.setAppName(AppName.HT.getDesc());

                StudentClockCache.put(studentClock);
              }
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
}
