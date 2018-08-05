package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ClockSettingMapper extends Mapper<ClockSetting> {
    int insertBatchSelective(List<ClockSetting> records);
}