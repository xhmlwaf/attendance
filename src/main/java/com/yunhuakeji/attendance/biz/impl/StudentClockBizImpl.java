package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.StudentClockBiz;
import com.yunhuakeji.attendance.cache.ClockAddressSettingCacheService;
import com.yunhuakeji.attendance.cache.ClockDaySettingCacheService;
import com.yunhuakeji.attendance.cache.ClockSettingCacheService;
import com.yunhuakeji.attendance.cache.StudentClockCache;
import com.yunhuakeji.attendance.constants.ConfigConstants;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.bizdao.model.*;
import com.yunhuakeji.attendance.dto.request.StudentClockAddReqDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockQueryRsqDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockStatRspDTO;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.StudentDeviceRefService;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.PositionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class StudentClockBizImpl implements StudentClockBiz {

    public static final Logger logger = LoggerFactory.getLogger(StudentClockBizImpl.class);

    @Autowired
    private ClockAddressSettingCacheService clockAddressSettingCacheService;

    @Autowired
    private ClockSettingCacheService clockSettingCacheService;

    @Autowired
    private StudentDeviceRefService studentDeviceRefService;

    @Autowired
    private StudentClockService studentClockService;

    @Autowired
    private ClockDaySettingService clockDaySettingService;

    @Autowired
    private ClockDaySettingCacheService clockDaySettingCacheService;


    @Override
    public Result clock(StudentClockAddReqDTO req) {
        //校验参数
        Long studentId = req.getStudentId();
        String deviceId = req.getDeviceId();
        BigDecimal lat = req.getPosLatitude();
        BigDecimal lon = req.getPosLongitude();
        //校验打卡时间
        List<ClockSetting> clockSettingList = clockSettingCacheService.list();
        if (CollectionUtils.isEmpty(clockSettingList)) {
            logger.error("未配置打卡时间");
            //TODO 抛出
        }
        ClockSetting clockSetting = clockSettingList.get(0);
        boolean checkTimeResult = checkTime(clockSetting);
        if (!checkTimeResult) {
            logger.warn("不在打卡时间范围");
            //TODO
        }

        //校验打卡地址
        List<ClockAddressSetting> clockAddressSettingList = clockAddressSettingCacheService.list();
        if (CollectionUtils.isEmpty(clockAddressSettingList)) {
            //throw new BusinessException(ErrorCode.CLOCK_ADDRESS_NOT_CONFIG);
        }
        boolean checkAddressResult = checkAddress(lat, lon, clockAddressSettingList);
        if (!checkAddressResult) {
            //throw new BusinessException(ErrorCode.CLOCK_ADDRESS_NOT_CONFIG);
        }
        //校验今天是否需要打卡
        List<Integer> allDayList = clockDaySettingCacheService.list();
        if (allDayList == null || allDayList.contains(DateUtil.currYmdToInt())) {
            //TODO 今天不需要打卡
        }

        Short deviceCheck = clockSetting.getDeviceCheck();
        if (ConfigConstants.CHECK_DEVICE_YES.equals(deviceCheck)) {
            List<StudentDeviceRef> studentDeviceRefList = studentDeviceRefService.list(studentId);
            boolean checkDeviceResult = checkDevice(deviceId, studentDeviceRefList);
            if (!checkDeviceResult) {
                logger.warn("不是常用打卡设备");
                //TODO
            }
        }

        long nowDate = Long.parseLong(DateUtil.dateToStr(new Date(), DateUtil.DATESTYLE_HHMMSS));
        List<StudentClock> studentClockList =
                studentClockService.list(studentId, nowDate);
        if (!CollectionUtils.isEmpty(studentClockList)) {
            logger.warn("今日已经打卡");
            //TODO
        }

        StudentClock studentClock = studentClockAddReqDTOToStudentClock(req);
        StudentClockCache.put(studentClock);

        return Result.success(null);
    }

    @Override
    public Result<StudentClockStatRspDTO> statClockByStudent(Long studentId) {

        StudentClockStatRspDTO rspDTO = new StudentClockStatRspDTO();
        int clockCount = studentClockService.count(studentId, ClockStatus.CLOCK.getType());
        int stayoutLateCount = studentClockService.count(studentId, ClockStatus.STAYOUT_LATE.getType());
        int stayoutCount = studentClockService.count(studentId, ClockStatus.STAYOUT.getType());
        rspDTO.setTotalClock(clockCount);
        rspDTO.setTotalStayOut(stayoutCount);
        rspDTO.setTotalStayOutLate(stayoutLateCount);

        return Result.success(rspDTO);
    }

    @Override
    public Result<List<StudentClockQueryRsqDTO>> listByYearMonth(Long studentId, Integer year, Integer month) {

        List<StudentClock> studentClockList = studentClockService.listByMonth(studentId, year, month);
        Map<Long, StudentClock> resultMap = convert(studentClockList);
        List<ClockDaySetting> clockDaySettingList = clockDaySettingService.list(year, month);
        List<StudentClockQueryRsqDTO> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(clockDaySettingList)) {
            for (ClockDaySetting setting : clockDaySettingList) {
                Integer yearMonthDay = DateUtil.ymdToint(setting.getYearMonth(), setting.getDay());
                StudentClock studentClock = resultMap.get(yearMonthDay.longValue());
                StudentClockQueryRsqDTO rsqDTO = new StudentClockQueryRsqDTO();
                rsqDTO.setDay(setting.getDay());
                rsqDTO.setClockStatus(studentClock == null ? ClockStatus.NOT_CLOCK.getType() : studentClock.getClockStatus().byteValue());
                rsqDTO.setLastUpdateTime(studentClock.getUpdateTime());
                rsqDTO.setMonth(setting.getYearMonth() % 100);
                rsqDTO.setYear(setting.getYearMonth() / 100);
                resultList.add(rsqDTO);
            }
        }

        return Result.success(resultList);
    }

    private Map<Long, StudentClock> convert(List<StudentClock> studentClockList) {
        Map<Long, StudentClock> resultMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(studentClockList)) {
            for (StudentClock sc : studentClockList) {
                resultMap.put(sc.getClockDate(), sc);
            }
        }
        return resultMap;
    }


    /**
     * 判断点是否在打卡区域内
     *
     * @param lat
     * @param lon
     * @param clockAddressSettingList
     * @return
     */
    private boolean checkAddress(BigDecimal lat,
                                 BigDecimal lon,
                                 List<ClockAddressSetting> clockAddressSettingList) {
        for (ClockAddressSetting cas : clockAddressSettingList) {
            boolean isInArea =
                    PositionUtil.isInCircle(cas.getLat().doubleValue(), cas.getLon().doubleValue(), cas.getRadius().doubleValue(), lat.doubleValue(), lon.doubleValue());
            if (isInArea) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断打卡时间范围
     *
     * @param clockSetting
     * @return
     */
    private boolean checkTime(ClockSetting clockSetting) {
        long startTime = clockSetting.getClockStartTime();
        long endTime = clockSetting.getClockEndTime();
        long nowDate = Long.parseLong(DateUtil.dateToStr(new Date(), DateUtil.DATESTYLE_HHMMSS));
        if (nowDate >= startTime && nowDate <= endTime) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设备校验
     *
     * @param deviceId
     * @param studentDeviceRefList
     * @return
     */
    private boolean checkDevice(String deviceId,
                                List<StudentDeviceRef> studentDeviceRefList) {
        if (CollectionUtils.isEmpty(studentDeviceRefList)) {
            return true;
        }
        for (StudentDeviceRef ref : studentDeviceRefList) {
            if (ref.getDeviceId().equals(deviceId)) {
                return true;
            }
        }
        return false;
    }

    private StudentClock studentClockAddReqDTOToStudentClock(StudentClockAddReqDTO req) {
        StudentClock studentClock = new StudentClock();
        studentClock.setUserId(req.getStudentId());
        studentClock.setDeviceId(req.getDeviceId());
        studentClock.setLat(req.getPosLatitude());
        studentClock.setLon(req.getPosLongitude());
        return studentClock;
    }
}
