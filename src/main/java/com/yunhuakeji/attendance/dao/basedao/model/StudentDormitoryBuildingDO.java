package com.yunhuakeji.attendance.dao.basedao.model;

public class StudentDormitoryBuildingDO {

  private long studentId;
  private long dormitoryId;
  private long buildingId;

  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }

  public long getDormitoryId() {
    return dormitoryId;
  }

  public void setDormitoryId(long dormitoryId) {
    this.dormitoryId = dormitoryId;
  }

  public long getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(long buildingId) {
    this.buildingId = buildingId;
  }
}
