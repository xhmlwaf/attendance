package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClock;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface InstructorClockMapper extends Mapper<InstructorClock> {
    int insertBatchSelective(List<InstructorClock> records);
}