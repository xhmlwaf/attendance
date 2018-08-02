package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class AnalysisExceptionClockByWeekRsqDTO extends StudentBaseInfoDTO {
    
    @ApiParam(name = "周未归次数")
    private int continuousStayoutDays;
    @ApiParam(name = "周晚归次数")
    private int continuousStayoutLateDays;

}
