package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;

import java.util.Date;
import java.util.List;

public interface ClockDaySettingService {

  List<ClockDaySetting> list(Integer year, Integer month, Byte day);

  List<ClockDaySetting> list(Integer year, Integer month);

  List<ClockDaySetting> listAll();

  List<ClockDaySetting> list(Date startDate, Date endDate);
}
