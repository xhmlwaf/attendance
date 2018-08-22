package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dto.response.InstructorStatRspDTO;

import java.io.Serializable;
import java.util.Comparator;

//根据dealCareCount排序
public class InstructorCompatator03 implements Comparator<InstructorStatRspDTO>,Serializable {
  @Override
  public int compare(InstructorStatRspDTO o1, InstructorStatRspDTO o2) {
    return o1.getDealCareCount()-o2.getDealCareCount();
  }
}