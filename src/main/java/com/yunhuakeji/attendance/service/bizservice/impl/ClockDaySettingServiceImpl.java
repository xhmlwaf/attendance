package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.dao.bizdao.ClockDaySettingMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClockDaySettingServiceImpl implements ClockDaySettingService {

    @Autowired
    private ClockDaySettingMapper clockDaySettingMapper;

    @Override
    public List<ClockDaySetting> list(Integer year, Integer month, Byte day) {
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

    @Override
    public List<ClockDaySetting> list(Date startDate, Date endDate) {
        int startYearMonth = DateUtil.getYearMonthByDate(startDate);
        int startDay = DateUtil.getDayByDate(startDate);
        int endYearMonth = DateUtil.getYearMonthByDate(endDate);
        int endDay = DateUtil.getDayByDate(endDate);
        Example example1 = new Example(ClockDaySetting.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andGreaterThanOrEqualTo("yearMonth",startYearMonth);
        criteria1.andGreaterThanOrEqualTo("day",startDay);

        Example example2 = new Example(ClockDaySetting.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andLessThanOrEqualTo("yearMonth",endYearMonth);
        criteria2.andLessThanOrEqualTo("day",endDay);

        List<ClockDaySetting> clockDaySettingList = new ArrayList<>();
        clockDaySettingList.addAll(clockDaySettingMapper.selectByExample(example1));
        clockDaySettingList.addAll(clockDaySettingMapper.selectByExample(example2));

        return clockDaySettingList;
    }

    @Override
    public List<ClockDaySetting> listFromCurrYearMonth(Integer yearMonth) {
        Example example = new Example(ClockDaySetting.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThanOrEqualTo("yearMonth", yearMonth);
        return clockDaySettingMapper.selectByExample(example);
    }


}
