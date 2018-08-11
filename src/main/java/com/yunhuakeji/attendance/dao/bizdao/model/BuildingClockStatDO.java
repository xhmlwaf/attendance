package com.yunhuakeji.attendance.dao.bizdao.model;

public class BuildingClockStatDO {

    private Long buildingId;
    private Byte statusClock;
    private int statCount;

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Byte getStatusClock() {
        return statusClock;
    }

    public void setStatusClock(Byte statusClock) {
        this.statusClock = statusClock;
    }

    public int getStatCount() {
        return statCount;
    }

    public void setStatCount(int statCount) {
        this.statCount = statCount;
    }
}
