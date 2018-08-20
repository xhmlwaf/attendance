package com.yunhuakeji.attendance.dao.basedao;

import com.yunhuakeji.attendance.dao.basedao.model.StatStudentByGender;
import com.yunhuakeji.attendance.dao.basedao.model.StudentDormitoryBuildingDO;
import com.yunhuakeji.attendance.dao.basedao.model.StudentInfo;

import com.yunhuakeji.attendance.dao.bizdao.model.BuildingStudentStatDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

public interface StudentInfoMapper extends Mapper<StudentInfo> {

  /**
   * 根据辅导员ID统计应打卡学生数量
   *
   * @param instructorId :
   * @return : int
   */
  List<Long> listStudentIdsByInstructorId(@Param("InstructorId") Long instructorId);

  List<Long> listStudentIdsByInstructorIdAndNOC(@Param("InstructorId") Long instructorId, @Param("nameOrCode") String nameOrCode);

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
  List<Long> listClockStudentByBuildingIds(List<Long> buildingIds);

  List<Long> listClockStudentByBuildingIdsAndNOC(Map<String,Object> queryMap);

  List<Long> listClockStudentByNOC(String nameOrCode);

  List<StatStudentByGender> statStudentByGender();

  List<BuildingStudentStatDO> statBuildingStudent(List<Long> buildingIds);

  int countClockStudentByClassIds(List<Long> classIds);

  List<StudentDormitoryBuildingDO> listStudentIdsOrderByBuilding();
}