package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.BigScreenMonitorBiz;
import com.yunhuakeji.attendance.cache.QrCodeCache;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.StatStudentByGender;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusGenderDO;
import com.yunhuakeji.attendance.dto.response.BigScreenMonitorStatRspDTO;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.enums.GenderType;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.baseservice.StudentInfoService;
import com.yunhuakeji.attendance.service.bizservice.ClockSettingService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BigScreenMonitorBizImpl implements BigScreenMonitorBiz {

  @Autowired
  private StudentInfoService studentInfoService;

  @Autowired
  private ClockSettingService clockSettingService;

  @Autowired
  private StudentClockService studentClockService;

  @Autowired
  private QrCodeCache qrCodeCache;

  @Override
  public Result<BigScreenMonitorStatRspDTO> getBigScreenMonitorStat() {

    //获取系统配置
    ClockSetting clockSetting = getClockSetting();
    //定义统计时间
    long statDate = 0;
    //比较当前时间和设置的查寝结束时间
    if (DateUtil.currHhmmssToLong() > clockSetting.getCheckDormEndTime()) {
      //昨天
      statDate = DateUtil.getYearMonthDayByDate(DateUtil.nowDateAdd(-1));
    } else {
      //前天
      statDate = DateUtil.getYearMonthDayByDate(DateUtil.nowDateAdd(-2));
    }
    Map<String, Object> queryMap = new HashMap<>();
    queryMap.put("clockDate", statDate);

    BigScreenMonitorStatRspDTO rspDTO = new BigScreenMonitorStatRspDTO();

    List<ClockStatByStatusGenderDO> clockStatByStatusGenderDOList = studentClockService.statByStatusGender(queryMap);
    if (!CollectionUtils.isEmpty(clockStatByStatusGenderDOList)) {
      for (ClockStatByStatusGenderDO item : clockStatByStatusGenderDOList) {
        if (GenderType.MALE.getType() == item.getGender() && ClockStatus.STAYOUT.getType() == item.getClockStatus()) {
          rspDTO.setStayoutMaleNum(item.getStatCount());
        } else if (GenderType.MALE.getType() == item.getGender() && ClockStatus.STAYOUT_LATE.getType() == item.getClockStatus()) {
          rspDTO.setStayoutLateMaleNum(item.getStatCount());
        } else if (GenderType.FEMALE.getType() == item.getGender() && ClockStatus.STAYOUT.getType() == item.getClockStatus()) {
          rspDTO.setStayoutFemaleNum(item.getStatCount());
        } else if (GenderType.FEMALE.getType() == item.getGender() && ClockStatus.STAYOUT_LATE.getType() == item.getClockStatus()) {
          rspDTO.setStayoutLateFemaleNum(item.getStatCount());
        }
      }
    }

    //计算总男女人数量
    List<StatStudentByGender> statStudentByGenderList = studentInfoService.statStudentByGender();
    Map<Byte, Integer> genderToCountMap = getGenderToCountMap(statStudentByGenderList);
    rspDTO.setTotalMaleNum(genderToCountMap.get(GenderType.MALE.getType()));
    rspDTO.setTotalFemaleNum(genderToCountMap.get(GenderType.FEMALE.getType()));

    return Result.success(rspDTO);
  }

  private ClockSetting getClockSetting() {
    //获取统计日期，根据配置的查寝时间来算
    List<ClockSetting> clockSettingList = clockSettingService.listClockSetting();
    if (CollectionUtils.isEmpty(clockSettingList)) {
      throw new BusinessException(ErrorCode.CHECK_TIME_NOT_CONFIG);
    }
    return clockSettingList.get(0);
  }

  private Map<Byte, Integer> getGenderToCountMap(List<StatStudentByGender> statStudentByGenderList) {
    Map<Byte, Integer> genderToCountMap = new HashMap<>();
    for (StatStudentByGender statStudentByGender : statStudentByGenderList) {
      genderToCountMap.put(statStudentByGender.getGenderType(), statStudentByGender.getStatCount());
    }
    return genderToCountMap;
  }

  @Override
  public Result<String> getCopyWriting() {
    //获取系统配置
    ClockSetting clockSetting = getClockSetting();
    return Result.success(clockSetting.getCarouselText());
  }

  @Override
  public void getQrcodeImg(HttpServletResponse response) {
    BufferedImage image = qrCodeCache.getImage();
    try {
      ImageIO.write(image, "jpeg", response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
