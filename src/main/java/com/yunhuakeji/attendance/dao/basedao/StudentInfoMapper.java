package com.yunhuakeji.attendance.dao.basedao;

import com.yunhuakeji.attendance.dao.basedao.model.StatStudentByGender;
import com.yunhuakeji.attendance.dao.basedao.model.StudentInfo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

public interface StudentInfoMapper extends Mapper<StudentInfo> {

  /**
   * 根据辅导员ID统计应打卡学生数量
   *
   * @param instructorId :
   * @return : int
   */
  List<Long> listStudentIdsByInstructorId(@Param("InstructorId") Long instructorId);

  /**
   * 统计所有应打卡学生数量
   *
   * @return : int
   */
  int countAllClockStudent();

  /**
   * 根据宿舍ID列表统计应打卡学生数量
   *
   * @param buildingIds :
   * @return : int
   */
  int countClockStudentByBuildingIds(List<Long> buildingIds);

  List<StatStudentByGender> statStudentByGender();
}