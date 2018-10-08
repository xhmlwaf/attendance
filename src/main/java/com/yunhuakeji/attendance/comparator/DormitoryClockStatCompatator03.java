package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dto.response.DormitoryClockStatDTO;
import java.io.Serializable;
import java.util.Comparator;

public class DormitoryClockStatCompatator03 implements Comparator<DormitoryClockStatDTO>,
    Serializable {

  @Override
  public int compare(DormitoryClockStatDTO o1, DormitoryClockStatDTO o2) {
    return o1.getLayOutLayStudent() - o2.getLayOutLayStudent();
  }
}
