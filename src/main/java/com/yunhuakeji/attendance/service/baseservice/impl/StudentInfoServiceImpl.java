package com.yunhuakeji.attendance.service.baseservice.impl;

import com.yunhuakeji.attendance.dao.basedao.StudentInfoMapper;
import com.yunhuakeji.attendance.dao.basedao.model.StatStudentByGender;
import com.yunhuakeji.attendance.dao.basedao.model.StudentInfo;
import com.yunhuakeji.attendance.dao.bizdao.model.BuildingStudentStatDO;
import com.yunhuakeji.attendance.service.baseservice.StudentInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.entity.Example;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {

  @Autowired
  private StudentInfoMapper studentInfoMapper;

  @Override
  public int insert(StudentInfo record) {
    return studentInfoMapper.insert(record);
  }

  @Override
  public int deleteByPrimaryKey(String id) {
    return studentInfoMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(StudentInfo record) {
    return studentInfoMapper.updateByPrimaryKeySelective(record);
  }

  @Override
  public StudentInfo selectByPrimaryKey(Long id) {
    return studentInfoMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<StudentInfo> selectByPrimaryKeyList(List<Long> ids) {
    Example example = new Example(StudentInfo.class);
    example.createCriteria().andIn("id", ids);
    return studentInfoMapper.selectByExample(example);
  }

  @Override
  public int countClockStudentByInstructorId(Long instructorId) {
    return studentInfoMapper.listStudentIdsByInstructorId(instructorId).size();
  }

  @Override
  public List<Long> listClockStudentByInstructorId(Long instructorId) {
    return studentInfoMapper.listStudentIdsByInstructorId(instructorId);
  }

  @Override
  public int countAllClockStudent() {
    return studentInfoMapper.countAllClockStudent();
  }

  @Override
  public int countClockStudentByBuildingIds(List<Long> buildingIds) {
    if (CollectionUtils.isEmpty(buildingIds)) {
      return 0;
    }
    List<Long> studentIds = studentInfoMapper.listClockStudentByBuildingIds(buildingIds);
    if (CollectionUtils.isEmpty(studentIds)) {
      return 0;
    }
    return studentIds.size();
  }

  @Override
  public List<Long> listClockStudentByBuildingIds(List<Long> buildingIds) {
    return studentInfoMapper.listClockStudentByBuildingIds(buildingIds);
  }

  @Override
  public List<StatStudentByGender> statStudentByGender() {
    return studentInfoMapper.statStudentByGender();
  }

  @Override
  public List<BuildingStudentStatDO> statBuildingStudent(List<Long> buildingIds) {
    return studentInfoMapper.statBuildingStudent(buildingIds);
  }

  @Override
  public List<Long> listStudentIdsByInstructorIdAndNOC(Long instructorId, String nameOrCode) {
    return studentInfoMapper.listStudentIdsByInstructorIdAndNOC(instructorId, nameOrCode);
  }

  @Override
  public List<Long> listClockStudentByBuildingIdsAndNOC(List<Long> buildingIds, String nameOrCode) {
    Map<String, Object> queryMap = new HashMap<>();
    queryMap.put("buildingIds", buildingIds);
    queryMap.put("nameOrCode", nameOrCode);
    return studentInfoMapper.listClockStudentByBuildingIdsAndNOC(queryMap);
  }

  @Override
  public List<Long> listClockStudentByNOC(String nameOrCode) {
    return studentInfoMapper.listClockStudentByNOC(nameOrCode);
  }

  @Override
  public int countClockStudentByClassIds(List<Long> classIds) {
    return studentInfoMapper.countClockStudentByClassIds(classIds);
  }

}
