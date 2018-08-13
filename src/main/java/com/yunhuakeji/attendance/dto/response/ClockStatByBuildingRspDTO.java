package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class ClockStatByBuildingRspDTO {

    @ApiModelProperty(value = "宿舍楼ID")
    private Long buildingId;
    @ApiModelProperty(value = "宿舍楼名称")
    private String buildingName;
    @ApiModelProperty(value = "打卡人数")
    private int clockCount = 0;
    @ApiModelProperty(value = "未打卡人数")
    private int notClockCount = 0;

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getClockCount() {
        return clockCount;
    }

    public void setClockCount(int clockCount) {
        this.clockCount = clockCount;
    }

    public int getNotClockCount() {
        return notClockCount;
    }

    public void setNotClockCount(int notClockCount) {
        this.notClockCount = notClockCount;
    }
}
