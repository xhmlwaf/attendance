package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByWeekRsqDTO;

import java.util.Comparator;

//stayoutLateDays
public class AnalysisExceptionClockByWeekCompatator02 implements Comparator<AnalysisExceptionClockByWeekRsqDTO> {
    @Override
    public int compare(AnalysisExceptionClockByWeekRsqDTO o1, AnalysisExceptionClockByWeekRsqDTO o2) {
        return o1.getStayoutLateDays()-o2.getStayoutLateDays();
    }
}
