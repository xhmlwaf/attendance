package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dto.response.StudentClockQueryRsqDTO;

import java.io.Serializable;
import java.util.Comparator;

public class StudentClockQueryRsqDTOCompatator01 implements Comparator<StudentClockQueryRsqDTO>, Serializable {
  @Override
  public int compare(StudentClockQueryRsqDTO o1, StudentClockQueryRsqDTO o2) {
    return o1.getClockDate().compareTo(o2.getClockDate());
  }
}
