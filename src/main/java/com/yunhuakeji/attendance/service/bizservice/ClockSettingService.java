package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;

import java.util.List;

public interface ClockSettingService {

    List<ClockSetting> listClockSetting();

    void update(ClockSetting clockSetting);
}
