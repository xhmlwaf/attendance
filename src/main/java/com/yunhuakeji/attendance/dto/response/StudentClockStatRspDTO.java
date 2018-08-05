package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 学生打卡统计
 */
public class StudentClockStatRspDTO {

    @ApiParam(name = "累计晚归")
    private int totalStayOutLate;

    @ApiParam(name = "累计到勤")
    private int totalClock;

    @ApiParam(name = "累计未归")
    private int totalStayOut;

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
}
