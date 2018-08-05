package com.yunhuakeji.attendance.cache;


import com.yunhuakeji.attendance.service.bizservice.ClockAddressSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 位置缓存服务
 */
@Service
public class ClockAddressSettingCacheService extends DataCacheService {

    @Autowired
    private ClockAddressSettingService clockAddressSettingService;

    @Override
    public List listAll() {
        return clockAddressSettingService.llstAll();
    }

    @Override
    public long getPeriod() {
        return 0;
    }
}
