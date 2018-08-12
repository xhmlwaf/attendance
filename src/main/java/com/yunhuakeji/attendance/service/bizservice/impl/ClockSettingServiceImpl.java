package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.dao.bizdao.ClockSettingMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.bizservice.ClockSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Override
    public ClockSetting getClockSetting() {
        //获取统计日期，根据配置的查寝时间来算
        List<ClockSetting> clockSettingList = clockSettingMapper.selectAll();
        if (CollectionUtils.isEmpty(clockSettingList)) {
            throw new BusinessException(ErrorCode.CHECK_TIME_NOT_CONFIG);
        }
        return clockSettingList.get(0);
    }
}
