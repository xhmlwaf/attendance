package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;

import java.util.List;

public interface ClockDaySettingService {

    List<ClockDaySetting> list(Integer year, Integer month, Short day);

    List<ClockDaySetting> list(Integer year, Integer month);

    List<ClockDaySetting> listAll();
}
