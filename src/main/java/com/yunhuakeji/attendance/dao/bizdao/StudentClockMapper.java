package com.yunhuakeji.attendance.dao.bizdao;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusDO;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface StudentClockMapper extends Mapper<StudentClock> {
  int insertBatchSelective(List<StudentClock> records);

  /**
   * 根据instructorId,buildingIds,clockDate统计
   *
   * @param queryMap :
   * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusDO>
   */
  List<ClockStatByStatusDO> statByStatus(Map<String, Object> queryMap);


  /**
   * 据instructorId,buildingIds,clockDate,CLOCK_STATUS查询学生ID
   *
   * @param queryMap :
   * @return : java.util.List<java.lang.Long>
   */
  List<Long> getStudentIds(Map<String, Object> queryMap);


}