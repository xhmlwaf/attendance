package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class DormitoryClockStatDTO {

  @ApiParam(name = "宿舍ID")
  private Long dormitoryId;
  @ApiParam(name = "宿舍名称")
  private String dormitoryName;
  @ApiParam(name = "楼栋ID")
  private Long buildingId;
  @ApiParam(name = "楼栋名称")
  private String buildingName;
  @ApiParam(name = "总人数")
  private int totalStudent;
  @ApiParam(name = "未归人数")
  private int layOutStudent;
  @ApiParam(name = "晚归人数")
  private int layOutLayStudent;
  @ApiParam(name = "是否已查寢")
  private Byte hasChecked;

  public Long getDormitoryId() {
    return dormitoryId;
  }

  public void setDormitoryId(Long dormitoryId) {
    this.dormitoryId = dormitoryId;
  }

  public String getDormitoryName() {
    return dormitoryName;
  }

  public void setDormitoryName(String dormitoryName) {
    this.dormitoryName = dormitoryName;
  }

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

  public int getTotalStudent() {
    return totalStudent;
  }

  public void setTotalStudent(int totalStudent) {
    this.totalStudent = totalStudent;
  }

  public int getLayOutStudent() {
    return layOutStudent;
  }

  public void setLayOutStudent(int layOutStudent) {
    this.layOutStudent = layOutStudent;
  }

  public int getLayOutLayStudent() {
    return layOutLayStudent;
  }

  public void setLayOutLayStudent(int layOutLayStudent) {
    this.layOutLayStudent = layOutLayStudent;
  }

  public Byte getHasChecked() {
    return hasChecked;
  }

  public void setHasChecked(Byte hasChecked) {
    this.hasChecked = hasChecked;
  }
}
