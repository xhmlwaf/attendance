package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByWeekRsqDTO;
import java.io.Serializable;
import java.util.Comparator;

//根据stayoutLateDays排序
public class AnalysisExceptionClockByWeekCompatator02 implements
    Comparator<AnalysisExceptionClockByWeekRsqDTO>, Serializable {

  @Override
  public int compare(AnalysisExceptionClockByWeekRsqDTO o1, AnalysisExceptionClockByWeekRsqDTO o2) {
    return o1.getStayoutLateDays() - o2.getStayoutLateDays();
  }
}
