package com.yunhuakeji.attendance.thread;

import com.alibaba.fastjson.JSON;
import com.yunhuakeji.attendance.cache.ClockDaySettingCacheService;
import com.yunhuakeji.attendance.cache.ClockSettingCacheService;
import com.yunhuakeji.attendance.cache.StudentClockCache;
import com.yunhuakeji.attendance.constants.ConfigConstants;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.enums.AppName;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.service.bizservice.RedisService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.impl.StudentClockServiceImpl;
import com.yunhuakeji.attendance.util.ApplicationUtils;
import com.yunhuakeji.attendance.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 自动转未归线程
 */
public class StayoutStatThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(StayoutStatThread.class);

    private static final HashMap<Integer, Object> statMap = new HashMap();

    private static final String REDIS_CLOCK_DAY_PREFIX = "CLOCK_DAY_";

    @Override
    public void run() {

        ClockDaySettingCacheService clockDaySettingCacheService = ApplicationUtils.getBean(ClockDaySettingCacheService.class);
        ClockSettingCacheService clockSettingCacheService = ApplicationUtils.getBean(ClockSettingCacheService.class);
        StudentClockService studentClockService = ApplicationUtils.getBean(StudentClockServiceImpl.class);
        RedisService redisService = ApplicationUtils.getBean(RedisService.class);

        while (true) {
            try {
                Integer currDate = (int)DateUtil.currYYYYMMddToLong();
                Thread.sleep(5000);
                //校验今天是否需要打卡
                List<Integer> allDayList = clockDaySettingCacheService.list();
                if (allDayList != null && allDayList.contains(currDate)) {
                } else {
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
                //logger.info("currTime:{},clockEndTime:{},map:{}", currTime, clockEndTime, statMap.get(currDate));
                if (currTime >= clockEndTime && statMap.get(currDate) == null) {
                    String result = redisService.getAndSet(REDIS_CLOCK_DAY_PREFIX + currDate, "running");
                    redisService.expire(REDIS_CLOCK_DAY_PREFIX + currDate, 60, TimeUnit.SECONDS);
                    logger.info("当前线程获取锁结果:" + result);
                    if (result == null) {
                        List<Long> studentIds = studentClockService.getNotClockStudentIds(currDate);
                        if (!CollectionUtils.isEmpty(studentIds)) {
                            for (Long studentId : studentIds) {
                                StudentClock studentClock = new StudentClock();
                                studentClock.setUserId(studentId);
                                studentClock.setClockStatus(ClockStatus.STAYOUT.getType());
                                studentClock.setOperatorName("系统");
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

    public static void main(String[] args) {
        System.out.println(DateUtil.currHhmmssToLong());
    }
}
