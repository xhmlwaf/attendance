package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 辅导员打卡统计
 */
public class InstructorClockStatRsqDTO {

    @ApiModelProperty(name = "打卡次数统计")
    private int totalClockCount;

    public int getTotalClockCount() {
        return totalClockCount;
    }

    public void setTotalClockCount(int totalClockCount) {
        this.totalClockCount = totalClockCount;
    }
}
