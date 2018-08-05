package com.yunhuakeji.attendance.service.bizservice.impl;

import java.util.List;
import com.yunhuakeji.attendance.dao.bizdao.ClockAddressSettingMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockAddressSetting;
import com.yunhuakeji.attendance.service.bizservice.ClockAddressSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClockAddressSettingServiceImpl implements ClockAddressSettingService {

    @Autowired
    private ClockAddressSettingMapper clockAddressSettingMapper;

    @Override
    public List<ClockAddressSetting> llstAll() {
        return clockAddressSettingMapper.selectAll();
    }
}
