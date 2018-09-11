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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import sun.misc.BASE64Encoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

@Service
public class BigScreenMonitorBizImpl implements BigScreenMonitorBiz {

  private static final Logger logger = LoggerFactory.getLogger(BigScreenMonitorBizImpl.class);

  @Autowired
  private StudentInfoService studentInfoService;

  @Autowired
  private ClockSettingService clockSettingService;

  @Autowired
  private StudentClockService studentClockService;

  @Autowired
  private QrCodeCache qrCodeCache;

  private static final String BASE64_IMAGE_PREFIX = "data:image/jpeg;base64,";

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
  public Result<String> getQrcodeImg() {
    BufferedImage image = qrCodeCache.getImage();
    try {
      if (image != null) {
        File f = new File("qr_code.jpg");
        ImageIO.write(image, "jpeg", f);
        return Result.success(BASE64_IMAGE_PREFIX + convertToBase64(f));
      }
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return Result.success();
  }

  private String convertToBase64(File f) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    InputStream in = null;
    byte[] data = null;
    //读取图片字节数组
    try {
      in = new FileInputStream(f);
      data = new byte[in.available()];
      in.read(data);
      in.close();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    //对字节数组Base64编码
    BASE64Encoder encoder = new BASE64Encoder();
    return encoder.encode(data);//返回Base64编码过的字节数组字符串
  }


}
