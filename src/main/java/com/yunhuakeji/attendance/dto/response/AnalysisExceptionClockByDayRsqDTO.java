package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class AnalysisExceptionClockByDayRsqDTO extends StudentBaseInfoDTO {

    @ApiParam(name = "考勤状态")
    private byte clockStatus;
    @ApiParam(name = "连续未归天数")
    private int continuousStayoutDays;
    @ApiParam(name = "连续晚归天数")
    private int continuousStayoutLateDays;

}
