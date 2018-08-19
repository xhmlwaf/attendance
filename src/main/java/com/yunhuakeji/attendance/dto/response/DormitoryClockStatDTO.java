package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class DormitoryClockStatDTO {

  @ApiModelProperty(value = "宿舍ID")
  private Long dormitoryId;
  @ApiModelProperty(value = "宿舍名称")
  private String dormitoryName;
  @ApiModelProperty(value = "楼栋ID")
  private Long buildingId;
  @ApiModelProperty(value = "楼栋名称")
  private String buildingName;
  @ApiModelProperty(value = "总人数")
  private int totalStudent;
  @ApiModelProperty(value = "未归人数")
  private int layOutStudent;
  @ApiModelProperty(value = "晚归人数")
  private int layOutLayStudent;
  @ApiModelProperty(value = "是否已查寢")
  private boolean hasChecked;

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

  public boolean isHasChecked() {
    return hasChecked;
  }

  public void setHasChecked(boolean hasChecked) {
    this.hasChecked = hasChecked;
  }
}
