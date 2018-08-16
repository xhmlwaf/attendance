package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dto.response.InstructorStatRspDTO;

import java.io.Serializable;
import java.util.Comparator;

//clockCount
public class InstructorCompatator02 implements Comparator<InstructorStatRspDTO>,Serializable {
  @Override
  public int compare(InstructorStatRspDTO o1, InstructorStatRspDTO o2) {
    return o1.getClockCount()-o2.getClockCount();
  }
}