package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.Care;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorCareCountStat;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CareMapper extends Mapper<Care> {
  int insertBatchSelective(List<Care> records);

  List<InstructorCareCountStat> instructorCareCountStat(List<Long> instructorIds);
}