package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClockDaySettingCacheService extends DataCacheService {

    @Autowired
    private ClockDaySettingService clockDaySettingService;

    @Override
    public List listAll() {
        List<ClockDaySetting> clockDaySettingList = clockDaySettingService.listAll();
        List<Integer> dayList = new ArrayList<>();
        for (ClockDaySetting setting : clockDaySettingList) {
            Integer yearMonthDay = DateUtil.ymdToint(setting.getYearMonth(), setting.getDay());
            dayList.add(yearMonthDay);
        }
        return dayList;
    }

    @Override
    public long getPeriod() {
        return 0;
    }
}
