package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ClockDaySettingMapper extends Mapper<ClockDaySetting> {
    int insertBatchSelective(List<ClockDaySetting> records);
}