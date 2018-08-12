package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class AnalysisExceptionClockByDayRsqDTO extends StudentBaseInfoDTO {

    @ApiModelProperty(name = "考勤状态 1未打卡，2到勤，3晚归，4未归")
    private byte clockStatus;
    @ApiModelProperty(name = "连续未归天数")
    private int continuousStayoutDays;
    @ApiModelProperty(name = "连续晚归天数")
    private int continuousStayoutLateDays;

    public byte getClockStatus() {
        return clockStatus;
    }

    public void setClockStatus(byte clockStatus) {
        this.clockStatus = clockStatus;
    }

    public int getContinuousStayoutDays() {
        return continuousStayoutDays;
    }

    public void setContinuousStayoutDays(int continuousStayoutDays) {
        this.continuousStayoutDays = continuousStayoutDays;
    }

    public int getContinuousStayoutLateDays() {
        return continuousStayoutLateDays;
    }

    public void setContinuousStayoutLateDays(int continuousStayoutLateDays) {
        this.continuousStayoutLateDays = continuousStayoutLateDays;
    }
}
