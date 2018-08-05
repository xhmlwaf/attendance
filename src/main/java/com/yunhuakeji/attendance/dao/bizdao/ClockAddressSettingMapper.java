package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockAddressSetting;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ClockAddressSettingMapper extends Mapper<ClockAddressSetting> {
    int insertBatchSelective(List<ClockAddressSetting> records);
}