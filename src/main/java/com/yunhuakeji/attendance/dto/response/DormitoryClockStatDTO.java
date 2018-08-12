package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class DormitoryClockStatDTO {

  @ApiModelProperty(name = "宿舍ID")
  private Long dormitoryId;
  @ApiModelProperty(name = "宿舍名称")
  private String dormitoryName;
  @ApiModelProperty(name = "楼栋ID")
  private Long buildingId;
  @ApiModelProperty(name = "楼栋名称")
  private String buildingName;
  @ApiModelProperty(name = "总人数")
  private int totalStudent;
  @ApiModelProperty(name = "未归人数")
  private int layOutStudent;
  @ApiModelProperty(name = "晚归人数")
  private int layOutLayStudent;
  @ApiModelProperty(name = "是否已查寢")
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
