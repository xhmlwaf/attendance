package com.yunhuakeji.attendance.dao.bizdao.model;

/**
 * 统计楼栋打卡的人数
 */
public class BuildingClockStatDO {

    private Long buildingId;
    private int clockStatCount;

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public int getClockStatCount() {
        return clockStatCount;
    }

    public void setClockStatCount(int clockStatCount) {
        this.clockStatCount = clockStatCount;
    }
}
