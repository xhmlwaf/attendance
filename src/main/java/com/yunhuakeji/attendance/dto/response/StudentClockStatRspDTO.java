package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 学生打卡统计
 */
public class StudentClockStatRspDTO {

    @ApiModelProperty(value = "累计晚归")
    private int totalStayOutLate;

    @ApiModelProperty(value = "累计到勤")
    private int totalClock;

    @ApiModelProperty(value = "累计未归")
    private int totalStayOut;

    @ApiModelProperty(value = "累计关怀次数")
    private int totalCaredCount;

    public int getTotalStayOutLate() {
        return totalStayOutLate;
    }

    public void setTotalStayOutLate(int totalStayOutLate) {
        this.totalStayOutLate = totalStayOutLate;
    }

    public int getTotalClock() {
        return totalClock;
    }

    public void setTotalClock(int totalClock) {
        this.totalClock = totalClock;
    }

    public int getTotalStayOut() {
        return totalStayOut;
    }

    public void setTotalStayOut(int totalStayOut) {
        this.totalStayOut = totalStayOut;
    }

    public int getTotalCaredCount() {
        return totalCaredCount;
    }

    public void setTotalCaredCount(int totalCaredCount) {
        this.totalCaredCount = totalCaredCount;
    }
}
