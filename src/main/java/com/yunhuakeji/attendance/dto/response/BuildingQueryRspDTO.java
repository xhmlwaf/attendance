package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 宿舍楼查询信息
 */
public class BuildingQueryRspDTO {

  @ApiModelProperty(value = "楼栋ID")
  private Long buildingId;
  @ApiModelProperty(value = "楼栋名称")
  private String buildingName;
  @ApiModelProperty(value = "楼层数")
  private int floorNumber;

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

  public int getFloorNumber() {
    return floorNumber;
  }

  public void setFloorNumber(int floorNumber) {
    this.floorNumber = floorNumber;
  }
}
