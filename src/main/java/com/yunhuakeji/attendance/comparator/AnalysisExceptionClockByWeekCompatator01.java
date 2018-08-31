package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByWeekRsqDTO;

import java.io.Serializable;
import java.util.Comparator;

//根据stayoutDays排序
public class AnalysisExceptionClockByWeekCompatator01 implements Comparator<AnalysisExceptionClockByWeekRsqDTO>,Serializable {
    @Override
    public int compare(AnalysisExceptionClockByWeekRsqDTO o1, AnalysisExceptionClockByWeekRsqDTO o2) {
        return o1.getStayoutDays()-o2.getStayoutDays();
    }
}
