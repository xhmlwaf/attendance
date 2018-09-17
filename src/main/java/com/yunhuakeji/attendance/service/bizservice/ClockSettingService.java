package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockAddressSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.exception.BusinessException;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

public interface ClockSettingService {

  List<ClockSetting> listClockSetting();

  void update(ClockSetting clockSetting);

  ClockSetting getClockSetting();

  void updateConfig(ClockSetting clockSetting, List<ClockAddressSetting> clockAddressSettingList, Set<ClockDaySetting> clockDaySettingList);
}
