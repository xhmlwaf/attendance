package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockHistory;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface StudentClockHistoryMapper extends Mapper<StudentClockHistory> {
    int insertBatchSelective(List<StudentClockHistory> records);
}