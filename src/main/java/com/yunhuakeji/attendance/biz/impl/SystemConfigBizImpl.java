package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.SystemConfigBiz;
import com.yunhuakeji.attendance.cache.ClockAddressSettingCacheService;
import com.yunhuakeji.attendance.cache.ClockDaySettingCacheService;
import com.yunhuakeji.attendance.cache.ClockSettingCacheService;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.bizdao.model.*;
import com.yunhuakeji.attendance.dto.request.AddressReqDTO;
import com.yunhuakeji.attendance.dto.request.PasswordUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.ScreenConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.SysConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.TermSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.SysConfigRspDTO;
import com.yunhuakeji.attendance.dto.response.TermRspDTO;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.interfaces.StatAuth;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.*;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SystemConfigBizImpl implements SystemConfigBiz {

  @Autowired
  private ClockSettingService clockSettingService;

  @Autowired
  private ClockDaySettingService clockDaySettingService;

  @Autowired
  private ClockAddressSettingService clockAddressSettingService;

  @Autowired
  private TermConfigService termConfigService;

  @Autowired
  private ClockSettingCacheService clockSettingCacheService;

  @Autowired
  private ClockDaySettingCacheService clockDaySettingCacheService;

  @Autowired
  private ClockAddressSettingCacheService clockAddressSettingCacheService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private RedisService redisService;

  @Override
  public Result updateSysConfig(SysConfigReqDTO reqDTO) {

    long checkDormEndTime = DateUtil.getHHMMSSByDateStr(reqDTO.getCheckDormEndTime());
    if (checkDormEndTime > 90000) {
      throw new BusinessException(ErrorCode.CHECK_DORM_TIME_MUST_BEFORE_NIGHT);
    }
    ClockSetting clockSetting = new ClockSetting();
    clockSetting.setId(DateUtil.uuid());
    clockSetting.setCheckDormEndTime(DateUtil.getHHMMSSByDateStr(reqDTO.getCheckDormEndTime()));
    clockSetting.setCheckDormStartTime(DateUtil.getHHMMSSByDateStr(reqDTO.getCheckDormStartTime()));
    clockSetting.setClockStartTime(DateUtil.getHHMMSSByDateStr(reqDTO.getClockStartTime()));
    clockSetting.setClockEndTime(DateUtil.getHHMMSSByDateStr(reqDTO.getClockEndTime()));
    clockSetting.setDeviceCheck(reqDTO.getCheckDevice());

    List<AddressReqDTO> addressReqDTOS = reqDTO.getAddressReqDTOList();
    List<ClockAddressSetting> clockAddressSettingList = ConvertUtil.getClockAddressSettingList(addressReqDTOS);

    List<ClockDaySetting> clockDaySettingList = new ArrayList<>();
    List<String> dayList = reqDTO.getDayList();
    if (!CollectionUtils.isEmpty(dayList)) {
      for (String dayStr : dayList) {
        Integer day = Integer.parseInt(dayStr);
        ClockDaySetting clockDaySetting = new ClockDaySetting();
        clockDaySetting.setYearMonth(day / 100);
        clockDaySetting.setDay(day % 100);
        clockDaySettingList.add(clockDaySetting);
      }
    }

    clockSettingService.updateConfig(clockSetting, clockAddressSettingList, clockDaySettingList);
    //更新之后清空内存中的配置
    clockSettingCacheService.clearCache();
    clockDaySettingCacheService.clearCache();
    clockAddressSettingCacheService.clearCache();

    return Result.success();
  }


  @Override
  public Result<SysConfigRspDTO> getSysConfig() {

    ClockSetting clockSetting = getClockSetting();
    SysConfigRspDTO dto = new SysConfigRspDTO();
    dto.setClockStartTime(DateUtil.hhmmssToTimeStr(clockSetting.getClockStartTime()));
    dto.setClockEndTime(DateUtil.hhmmssToTimeStr(clockSetting.getClockEndTime()));
    dto.setCheckClockStartTime(DateUtil.hhmmssToTimeStr(clockSetting.getCheckDormStartTime()));
    dto.setCheckClockEndTime(DateUtil.hhmmssToTimeStr(clockSetting.getCheckDormEndTime()));
    dto.setCheckDevice(clockSetting.getDeviceCheck());

    List<ClockAddressSetting> clockAddressSettingList = clockAddressSettingService.llstAll();
    dto.setClockAddressSettingList(clockAddressSettingList);
    return Result.success(dto);
  }

  @Override
  public Result updateScreenConfig(ScreenConfigReqDTO reqDTO) {
    ClockSetting clockSetting = getClockSetting();
    clockSetting.setCarouselText(reqDTO.getCarouselText());
    clockSettingService.update(clockSetting);
    return Result.success();
  }

  @Override
  public Result<List<Integer>> listDaysByYearAndMonth(Integer year, Integer month) {
    List<ClockDaySetting> clockDaySettingList = clockDaySettingService.list(year, month);
    List<Integer> dayList = getDayList(clockDaySettingList);
    return Result.success(dayList);
  }

  @Override
  public Result<String> getScreenConfig() {
    ClockSetting clockSetting = getClockSetting();
    return Result.success(clockSetting.getCarouselText());
  }

  @Override
  public Result termSave(TermSaveReqDTO reqDTO) {
    // 校验时间重复
    List<TermConfig> termConfigList = termConfigService.listAll();
    boolean isRepeat = false;
    if (!CollectionUtils.isEmpty(termConfigList)) {
      for (TermConfig termConfig : termConfigList) {
        if (DateUtil.strToDate(reqDTO.getStartDate(), DateUtil.DATESTYLE_YYYY_MM_DD).getTime() >= termConfig.getStartDate().getTime() &&
            DateUtil.strToDate(reqDTO.getStartDate(), DateUtil.DATESTYLE_YYYY_MM_DD).getTime() <= termConfig.getEndDate().getTime()) {
          isRepeat = true;
        }
        if (DateUtil.strToDate(reqDTO.getEndDate(), DateUtil.DATESTYLE_YYYY_MM_DD).getTime() >= termConfig.getStartDate().getTime() &&
            DateUtil.strToDate(reqDTO.getEndDate(), DateUtil.DATESTYLE_YYYY_MM_DD).getTime() <= termConfig.getEndDate().getTime()) {
          isRepeat = true;
        }
      }
    }
    if (isRepeat) {
      throw new BusinessException(ErrorCode.ADD_TERM_TIME_REPEATED);
    }

    TermConfig termConfig = new TermConfig();
    termConfig.setId(DateUtil.uuid());
    termConfig.setEndYear(reqDTO.getEndYear());
    termConfig.setStartYear(reqDTO.getStartYear());
    termConfig.setTermNumber(reqDTO.getTermNumber());
    termConfig.setStartDate(DateUtil.strToDate(reqDTO.getStartDate(), DateUtil.DATESTYLE_YYYY_MM_DD));
    termConfig.setEndDate(DateUtil.strToDate(reqDTO.getEndDate(), DateUtil.DATESTYLE_YYYY_MM_DD));
    termConfigService.insert(termConfig);
    return Result.success();
  }

  @Override
  public Result<List<TermRspDTO>> listTerm() {
    List<TermConfig> termConfigList = termConfigService.listAll();
    List<TermRspDTO> termRspDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(termConfigList)) {
      LinkedHashMap<Long, TermRspDTO> map = new LinkedHashMap();
      for (TermConfig termConfig : termConfigList) {
        long yearMonth = termConfig.getStartYear() * 10000 + termConfig.getEndYear();
        TermRspDTO rspDTO = map.get(yearMonth);
        if (rspDTO == null) {
          rspDTO = new TermRspDTO();
          rspDTO.setStartYear(termConfig.getStartYear());
          rspDTO.setEndYear(termConfig.getEndYear());
        }
        if (termConfig.getTermNumber() == 1) {
          rspDTO.setTermOneStartDate(termConfig.getStartDate());
          rspDTO.setTermOneEndDate(termConfig.getEndDate());
        } else if (termConfig.getTermNumber() == 2) {
          rspDTO.setTermTwoStartDate(termConfig.getStartDate());
          rspDTO.setTermTwoEndDate(termConfig.getEndDate());
        }
        map.put(yearMonth, rspDTO);
      }
      for (Map.Entry<Long, TermRspDTO> entry : map.entrySet()) {
        termRspDTOList.add(entry.getValue());
      }
    }
    return Result.success(termRspDTOList);
  }

  @Override
  public Result<List<Integer>> listClockDayFromCurr() {
    int currYearMonth = DateUtil.currYearMonth();
    List<Integer> dayList = new ArrayList<>();
    List<ClockDaySetting> clockDaySettingList = clockDaySettingService.listFromCurrYearMonth(currYearMonth);
    if (!CollectionUtils.isEmpty(clockDaySettingList)) {
      for (ClockDaySetting clockDaySetting : clockDaySettingList) {
        int day = clockDaySetting.getYearMonth() * 100 + clockDaySetting.getDay();
        dayList.add(day);
      }
    }
    Collections.sort(dayList);
    return Result.success(dayList);
  }

  @Override
  public Result updatePwd(PasswordUpdateReqDTO reqDTO) {
    Long userId = null;
    try {
      userId = Long.parseLong(redisService.get(reqDTO.getToken()).toString());
    } catch (NumberFormatException e) {
      throw new BusinessException(ErrorCode.TOKEN_IS_INVALID);
    }
    Account account = accountService.getAccountByUserId(userId);
    boolean match = PasswordUtil.checkPwd(reqDTO.getOldPassword(), account.getPassword());
    if (!match) {
      throw new BusinessException(ErrorCode.PASSWORD_ERROR);
    }
    account.setPassword(PasswordUtil.hashPwd(reqDTO.getNewPassword()));
    accountService.updateAccount(account);
    return Result.success();
  }

  private List<Integer> getDayList(List<ClockDaySetting> clockDaySettingList) {
    if (!CollectionUtils.isEmpty(clockDaySettingList)) {
      return clockDaySettingList.stream().map(e -> e.getDay()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  private ClockSetting getClockSetting() {
    //获取统计日期，根据配置的查寝时间来算
    List<ClockSetting> clockSettingList = clockSettingService.listClockSetting();
    if (CollectionUtils.isEmpty(clockSettingList)) {
      throw new BusinessException(ErrorCode.CHECK_TIME_NOT_CONFIG);
    }
    return clockSettingList.get(0);
  }
}
