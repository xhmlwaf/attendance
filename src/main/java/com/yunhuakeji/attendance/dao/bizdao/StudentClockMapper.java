package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentClockMapper extends Mapper<StudentClock> {
    int insertBatchSelective(List<StudentClock> records);
}