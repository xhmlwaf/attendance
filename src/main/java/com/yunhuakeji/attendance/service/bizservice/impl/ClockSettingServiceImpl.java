package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.dao.bizdao.ClockSettingMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.service.bizservice.ClockSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClockSettingServiceImpl implements ClockSettingService {

    @Autowired
    private ClockSettingMapper clockSettingMapper;

    @Override
    public List<ClockSetting> listClockSetting() {
        return clockSettingMapper.selectAll();
    }

    @Override
    public void update(ClockSetting clockSetting) {
        clockSettingMapper.updateByPrimaryKeySelective(clockSetting);
    }
}
