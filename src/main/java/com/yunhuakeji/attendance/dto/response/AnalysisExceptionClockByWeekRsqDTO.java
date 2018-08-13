package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class AnalysisExceptionClockByWeekRsqDTO extends StudentBaseInfoDTO {
    
    @ApiModelProperty(value = "周未归次数")
    private int stayoutDays;
    @ApiModelProperty(value = "周晚归次数")
    private int stayoutLateDays;

    public int getStayoutDays() {
        return stayoutDays;
    }

    public void setStayoutDays(int stayoutDays) {
        this.stayoutDays = stayoutDays;
    }

    public int getStayoutLateDays() {
        return stayoutLateDays;
    }

    public void setStayoutLateDays(int stayoutLateDays) {
        this.stayoutLateDays = stayoutLateDays;
    }
}
