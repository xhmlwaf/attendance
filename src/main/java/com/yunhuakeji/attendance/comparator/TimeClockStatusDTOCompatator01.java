package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dto.response.TimeClockStatusDTO;

import java.io.Serializable;
import java.util.Comparator;

public class TimeClockStatusDTOCompatator01 implements Comparator<TimeClockStatusDTO>, Serializable {
  @Override
  public int compare(TimeClockStatusDTO o1, TimeClockStatusDTO o2) {
    return o1.getYear() * 10000 * o1.getMonth() * 100 + o1.getDay() - o2.getYear() * 10000 * o2.getMonth() * 100 + o2.getDay();
  }
}
