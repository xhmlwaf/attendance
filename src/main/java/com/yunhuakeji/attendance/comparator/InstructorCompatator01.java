package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dto.response.InstructorStatRspDTO;

import java.io.Serializable;
import java.util.Comparator;

//responsibleStudent
public class InstructorCompatator01 implements Comparator<InstructorStatRspDTO>,Serializable {
  @Override
  public int compare(InstructorStatRspDTO o1, InstructorStatRspDTO o2) {
    return o1.getResponsibleStudent()-o2.getResponsibleStudent();
  }
}