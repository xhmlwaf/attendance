package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.StudentClockBiz;
import com.yunhuakeji.attendance.cache.ClockAddressSettingCacheService;
import com.yunhuakeji.attendance.cache.ClockDaySettingCacheService;
import com.yunhuakeji.attendance.cache.ClockSettingCacheService;
import com.yunhuakeji.attendance.cache.StudentClockCache;
import com.yunhuakeji.attendance.comparator.StudentClockQueryRsqDTOCompatator01;
import com.yunhuakeji.attendance.comparator.TimeClockStatusDTOCompatator01;
import com.yunhuakeji.attendance.constants.ConfigConstants;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockAddressSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockDTO;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockHistory;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentDeviceRef;
import com.yunhuakeji.attendance.dao.bizdao.model.TermConfig;
import com.yunhuakeji.attendance.dto.request.StudentClockAddReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentClockBatchUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentClockUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.UpdateClockDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockQueryRsqDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockStatRspDTO;
import com.yunhuakeji.attendance.dto.response.TimeClockStatusDTO;
import com.yunhuakeji.attendance.dto.response.WeekInfoRspDTO;
import com.yunhuakeji.attendance.enums.AppName;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.bizservice.CareService;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.service.bizservice.ClockSettingService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.StudentDeviceRefService;
import com.yunhuakeji.attendance.service.bizservice.TermConfigService;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.PositionUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

  @Autowired
  private TermConfigService termConfigService;

  @Autowired
  private ClockSettingService clockSettingService;

  @Autowired
  private CareService careService;

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
      throw new BusinessException(ErrorCode.CLOCK_TIME_NOT_CONFIG);
    }
    ClockSetting clockSetting = clockSettingList.get(0);
    boolean checkTimeResult = checkTime(clockSetting);
    if (!checkTimeResult) {
      logger.warn("不在打卡时间范围");
      throw new BusinessException(ErrorCode.NOT_IN_TIME_RANGE);
    }

    //校验打卡地址
    List<ClockAddressSetting> clockAddressSettingList = clockAddressSettingCacheService.list();
    if (CollectionUtils.isEmpty(clockAddressSettingList)) {
      throw new BusinessException(ErrorCode.CLOCK_ADDRESS_NOT_CONFIG);
    }
    boolean checkAddressResult = checkAddress(lat, lon, clockAddressSettingList);
    if (!checkAddressResult) {
      throw new BusinessException(ErrorCode.CLOCK_NOT_IN_CONFIG_AREA);
    }
    //校验今天是否需要打卡
    List<Integer> allDayList = clockDaySettingCacheService.list();
    if (allDayList == null || allDayList.contains(DateUtil.getCurrDay())) {
      throw new BusinessException(ErrorCode.NOT_NEED_TO_CLOCK);
    }

    Byte deviceCheck = clockSetting.getDeviceCheck();
    if (ConfigConstants.CHECK_DEVICE_YES.equals(deviceCheck)) {
      List<StudentDeviceRef> studentDeviceRefList = studentDeviceRefService.list(studentId);
      boolean checkDeviceResult = checkDevice(deviceId, studentDeviceRefList);
      if (!checkDeviceResult) {
        logger.warn("不是常用打卡设备");
        throw new BusinessException(ErrorCode.DEVICE_ERROR);
      }
      if (CollectionUtils.isEmpty(studentDeviceRefList)) {
        StudentDeviceRef studentDeviceRef = new StudentDeviceRef();
        studentDeviceRef.setStudentId(studentId);
        studentDeviceRef.setDeviceId(req.getDeviceId());
        studentDeviceRefService.save(studentDeviceRef);
      }
    }

    List<StudentClock> studentClockList =
        studentClockService.list(studentId, DateUtil.currYYYYMMddToLong());
    if (!CollectionUtils.isEmpty(studentClockList)) {
      logger.warn("今日已经打卡");
      throw new BusinessException(ErrorCode.INSTRUCTOR_HAS_CLOCK);
    }

    StudentClock studentClock = studentClockAddReqDTOToStudentClock(req);
    StudentClockDTO studentClockDTO = new StudentClockDTO();
    BeanUtils.copyProperties(studentClock, studentClockDTO);
    StudentClockCache.put(studentClockDTO);

    return Result.success(null);
  }

  @Override
  public Result<StudentClockStatRspDTO> statClockByStudent(Long studentId) {

    StudentClockStatRspDTO rspDTO = new StudentClockStatRspDTO();
    int clockCount = studentClockService.count(studentId, ClockStatus.CLOCK.getType());
    int stayoutLateCount = studentClockService.count(studentId, ClockStatus.STAYOUT_LATE.getType());
    int stayoutCount = studentClockService.count(studentId, ClockStatus.STAYOUT.getType());
    int caredCount = careService.countByStudentIds(studentId);
    rspDTO.setTotalClock(clockCount);
    rspDTO.setTotalStayOut(stayoutCount);
    rspDTO.setTotalStayOutLate(stayoutLateCount);
    rspDTO.setTotalCaredCount(caredCount);
    return Result.success(rspDTO);
  }

  @Override
  public Result<List<StudentClockQueryRsqDTO>> listByYearMonth(Long studentId, Integer year,
      Integer month) {

    List<StudentClock> studentClockList = studentClockService.listByMonth(studentId, year, month);
    Map<Long, StudentClock> resultMap = ConvertUtil.getStudentClockMap(studentClockList);
    List<ClockDaySetting> clockDaySettingList = clockDaySettingService.list(year, month);
    List<StudentClockQueryRsqDTO> resultList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(clockDaySettingList)) {
      long currDate = DateUtil.currYYYYMMddToLong();
      for (ClockDaySetting setting : clockDaySettingList) {
        long yearMonthDay = DateUtil.ymdTolong(setting.getYearMonth(), setting.getDay());
        if (yearMonthDay >= currDate) {
          continue;
        }
        StudentClock studentClock = resultMap.get(yearMonthDay);
        StudentClockQueryRsqDTO rsqDTO = new StudentClockQueryRsqDTO();
        rsqDTO.setDay(setting.getDay());
        if (studentClock != null) {
          rsqDTO.setLastUpdateTime(studentClock.getUpdateTime());
          rsqDTO.setClockDate(DateUtil.strToDate("" + yearMonthDay, "yyyyMMdd"));
          rsqDTO.setClockStatus(studentClock.getClockStatus());
          rsqDTO.setOperateAppName(studentClock.getAppName());
          rsqDTO.setOperatorName(studentClock.getOperatorName());
        } else {
          rsqDTO.setClockStatus(ClockStatus.NOT_CLOCK.getType());
          rsqDTO.setClockDate(DateUtil.strToDate("" + yearMonthDay, "yyyyMMdd"));
        }
        rsqDTO.setMonth(setting.getYearMonth() % 100);
        rsqDTO.setYear(setting.getYearMonth() / 100);

        resultList.add(rsqDTO);
      }
    }
    if (!CollectionUtils.isEmpty(resultList)) {
      resultList.sort(new StudentClockQueryRsqDTOCompatator01());
    }

    return Result.success(resultList);
  }


  @Override
  public Result update(StudentClockUpdateReqDTO reqDTO) {

    long clockDate;
    if (reqDTO.getClockDate() == null) {
      //获取打卡设置
      ClockSetting clockSetting = clockSettingService.getClockSetting();
      //得到当前查寝日期
      clockDate = ConvertUtil.getCurrCheckDormitoryDay(clockSetting);
    } else {
      clockDate = Long.parseLong(reqDTO.getClockDate());
    }

    StudentClock studentClock = studentClockService
        .getByStudentIdAndDate(reqDTO.getId(), clockDate);
    if (studentClock == null) {
      studentClock = new StudentClock();
    }
    studentClock.setUserId(reqDTO.getId());
    studentClock.setClockStatus(reqDTO.getStatus());
    studentClock.setUpdateTime(new Date());
    AppName appName = AppName.get(reqDTO.getAppType());
    if (appName != null) {
      studentClock.setAppName(appName.getDesc());
    }
    if (reqDTO.getOperatorName() == null || AppName.HT.getType() == reqDTO.getAppType()) {
      studentClock.setOperatorName("系统");
    } else {
      studentClock.setOperatorName(reqDTO.getOperatorName());
    }
    studentClock.setOperatorId(
        reqDTO.getOperatorId() == null ? ConfigConstants.ADMIN_USER_ID : reqDTO.getOperatorId());
    studentClock.setClockDate(clockDate);

    StudentClockHistory studentClockHistory = new StudentClockHistory();
    studentClockHistory.setId(DateUtil.uuid());
    if (appName != null) {
      studentClockHistory.setAppName(appName.getDesc());
    }
    studentClockHistory.setClockStatus(reqDTO.getStatus());
    studentClockHistory.setOperateTime(new Date());
    if (reqDTO.getOperatorId() == null) {
      studentClockHistory.setOperatorId(ConfigConstants.ADMIN_USER_ID);
    } else {
      studentClockHistory.setOperatorId(reqDTO.getOperatorId());
    }
    studentClockHistory.setStatDate(clockDate);
    studentClockHistory.setUserId(studentClock.getUserId());
    if (reqDTO.getOperatorName() != null) {
      studentClockHistory.setOperatorName(reqDTO.getOperatorName());
    } else {
      studentClockHistory.setOperatorName("系统");
    }
    studentClockHistory.setRemark(reqDTO.getRemark());
    StudentClockDTO studentClockDTO = new StudentClockDTO();
    BeanUtils.copyProperties(studentClock, studentClockDTO);
    studentClockDTO.setRemark(reqDTO.getRemark());

    studentClockService.updateClock(studentClockDTO, studentClockHistory);
    return Result.success();
  }

  @Override
  public Result<Byte> getStudentClockStatusByDay(Long studentId) {
    ClockSetting clockSetting = clockSettingService.getClockSetting();
    long checkDormEndTime = clockSetting.getCheckDormEndTime();
    long currTime = DateUtil.currHhmmssToLong();
    long statTime;
    if (currTime <= checkDormEndTime) {
      statTime = DateUtil.getYearMonthDayByDate(DateUtil.add(new Date(), Calendar.DAY_OF_YEAR, -1));
    } else {
      statTime = DateUtil.currYYYYMMddToLong();
    }
    List<StudentClock> studentClockList = studentClockService.list(studentId, statTime);
    if (CollectionUtils.isEmpty(studentClockList)) {
      return Result.success(ClockStatus.NOT_CLOCK.getType());
    }
    return Result.success(studentClockList.get(0).getClockStatus());
  }

  @Override
  public Result<List<TimeClockStatusDTO>> listByWeekNumber(Long studentId, int weekNumber) {
    TermConfig termConfig = termConfigService.getLastTermConfig();
    if (termConfig == null) {
      logger.warn("不在学期内");
      return Result.success(new ArrayList<>());
    }
    Date startDate = termConfig.getStartDate();
    Date endDate = termConfig.getEndDate();

    WeekInfoRspDTO weekInfoRspDTO = ConvertUtil.getWeek(startDate, endDate, weekNumber);
    if (weekInfoRspDTO == null) {
      logger.warn("周数不存在");
      return Result.success();
    }
    Date startStatDate = DateUtil.add(weekInfoRspDTO.getStartDate(), Calendar.DAY_OF_YEAR, -1);
    Date endStatDate = DateUtil.add(weekInfoRspDTO.getEndDate(), Calendar.DAY_OF_YEAR, -1);
    long startStat = DateUtil.getYearMonthDayByDate(startStatDate);
    long endStat = DateUtil.getYearMonthDayByDate(endStatDate);
    List<StudentClock> studentClockList = studentClockService
        .listByTimeRange(studentId, startStat, endStat);
    List<TimeClockStatusDTO> timeClockStatusDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(studentClockList)) {
      long currDate = DateUtil.currYYYYMMddToLong();
      for (StudentClock studentClock : studentClockList) {
        if (studentClock.getClockDate() >= currDate) {
          continue;
        }
        TimeClockStatusDTO dto = new TimeClockStatusDTO();
        dto.setClockDate(DateUtil.strToDate(studentClock.getClockDate() + "", "yyyyMMdd"));
        dto.setClockStatus(studentClock.getClockStatus());
        dto.setYear(DateUtil.getYearByDate(studentClock.getClockTime()));
        dto.setMonth(DateUtil.getMonthByDate(studentClock.getClockTime()));
        dto.setDay(DateUtil.getDayByDate(studentClock.getClockTime()));
        timeClockStatusDTOList.add(dto);
      }
      timeClockStatusDTOList.sort(new TimeClockStatusDTOCompatator01());
    }

    return Result.success(timeClockStatusDTOList);
  }

  @Override
  public Result<Boolean> checkPosition(BigDecimal posLongitude, BigDecimal posLatitude) {
    //校验打卡地址
    List<ClockAddressSetting> clockAddressSettingList = clockAddressSettingCacheService.list();
    if (CollectionUtils.isEmpty(clockAddressSettingList)) {
      throw new BusinessException(ErrorCode.CLOCK_ADDRESS_NOT_CONFIG);
    }
    boolean checkAddressResult = checkAddress(posLatitude, posLongitude, clockAddressSettingList);
    return Result.success(checkAddressResult);
  }

  @Override
  public Result<Boolean> checkDevice(Long studentId, String deviceId) {
    List<StudentDeviceRef> studentDeviceRefs = studentDeviceRefService.list(studentId);
    if (CollectionUtils.isEmpty(studentDeviceRefs)) {
      return Result.success(true);
    }
    for (StudentDeviceRef ref : studentDeviceRefs) {
      if (ref.getDeviceId().equals(deviceId)) {
        return Result.success(true);
      }
    }
    return Result.success(false);
  }

  @Override
  public Result updateBatch(@Valid StudentClockBatchUpdateReqDTO reqDTO) {
    Byte appType = reqDTO.getAppType();
    Long operatorId = reqDTO.getOperatorId();
    String operatorName = reqDTO.getOperatorName();
    List<UpdateClockDTO> updateClockDTOS = reqDTO.getUpdateClockDTOList();
    if (!CollectionUtils.isEmpty(updateClockDTOS)) {
      for (UpdateClockDTO updateClockDTO : updateClockDTOS) {
        StudentClockUpdateReqDTO dto = new StudentClockUpdateReqDTO();
        dto.setId(updateClockDTO.getStudentId());
        dto.setAppType(appType);
        dto.setOperatorId(operatorId);
        dto.setOperatorName(operatorName);
        dto.setRemark(updateClockDTO.getRemark());
        dto.setStatus(updateClockDTO.getStatus());
        update(dto);
      }
    }
    return Result.success();
  }


  /**
   * 判断点是否在打卡区域内
   *
   * @param lat :
   * @param lon :
   * @param clockAddressSettingList :
   * @return : boolean
   */
  private boolean checkAddress(BigDecimal lat,
      BigDecimal lon,
      List<ClockAddressSetting> clockAddressSettingList) {
    try {
      for (ClockAddressSetting cas : clockAddressSettingList) {
        boolean isInArea =
            PositionUtil.isInCircle(cas.getLat().doubleValue(), cas.getLon().doubleValue(),
                cas.getRadius().doubleValue(), lat.doubleValue(), lon.doubleValue());
        if (isInArea) {
          return true;
        }
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return false;
    }
    return false;
  }


  /**
   * 判断打卡时间范围
   *
   * @param clockSetting :
   * @return : boolean
   */
  private boolean checkTime(ClockSetting clockSetting) {
    long startTime = clockSetting.getClockStartTime();
    long endTime = clockSetting.getClockEndTime();
    long nowDate = Long.parseLong(DateUtil.dateToStr(new Date(), DateUtil.DATESTYLE_HHMMSS));
    return nowDate >= startTime && nowDate <= endTime;
  }

  /**
   * 设备校验
   *
   * @param deviceId :
   * @param studentDeviceRefList :
   * @return : boolean
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
    studentClock.setClockStatus(ClockStatus.CLOCK.getType());
    return studentClock;
  }
}
