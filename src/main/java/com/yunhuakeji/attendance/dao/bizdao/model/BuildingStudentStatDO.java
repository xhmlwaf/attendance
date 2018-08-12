package com.yunhuakeji.attendance.dao.bizdao.model;

/**
 * 楼栋总人数(应打卡总人数)
 */
public class BuildingStudentStatDO {

    private long buildingId;
    private int studentTotalCount;

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public int getStudentTotalCount() {
        return studentTotalCount;
    }

    public void setStudentTotalCount(int studentTotalCount) {
        this.studentTotalCount = studentTotalCount;
    }
}
