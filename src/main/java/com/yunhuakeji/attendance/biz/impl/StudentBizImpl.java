package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.StudentBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.service.baseservice.StudentInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentBizImpl implements StudentBiz {

  @Autowired
  private StudentInfoService studentInfoService;

  @Override
  public Result<StudentBaseInfoDTO> getStudentBaseInfo(Long id) {

    StudentBaseInfoDTO dto = new StudentBaseInfoDTO();
    // TODO StudetnInfo 表主键不是id ，studentInfoService.selectByPrimaryKey(id)

    return null;
  }
}
