package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class DormitoryAdminQueryRspDTO extends StaffBaseInfoDTO {

  private String code;
  @ApiParam(name = "楼栋ID")
  private Long buildingId;
  @ApiParam(name = "楼栋名称")
  private String buildingName;

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
}
