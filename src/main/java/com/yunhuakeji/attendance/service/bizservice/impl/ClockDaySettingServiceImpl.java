package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.dao.bizdao.ClockDaySettingMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ClockDaySettingServiceImpl implements ClockDaySettingService {

    @Autowired
    private ClockDaySettingMapper clockDaySettingMapper;

    @Override
    public List<ClockDaySetting> list(Integer year, Integer month, Short day) {
        Integer yearMonth = DateUtil.getYearMonth(year, month);
        Example example = new Example(ClockDaySetting.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("yearMonth", yearMonth);
        criteria.andEqualTo("day", day);
        return clockDaySettingMapper.selectByExample(example);
    }

    @Override
    public List<ClockDaySetting> list(Integer year, Integer month) {
        Integer yearMonth = DateUtil.getYearMonth(year, month);
        Example example = new Example(ClockDaySetting.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("yearMonth", yearMonth);
        return clockDaySettingMapper.selectByExample(example);
    }

    @Override
    public List<ClockDaySetting> listAll() {
        Example example = new Example(ClockDaySetting.class);
        return clockDaySettingMapper.selectByExample(example);
    }


}
