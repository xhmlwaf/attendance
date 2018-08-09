package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;

public class DormitoryCheckOverReqDTO {

  @ApiParam(name = "宿舍ID", required = true)
  @NotNull(message = "宿舍ID不能为空")
  private Long dormitoryId;
  @ApiParam(name = "用户ID", required = true)
  @NotNull(message = "用户ID不能为空")
  private Long operatorId;

  public Long getDormitoryId() {
    return dormitoryId;
  }

  public void setDormitoryId(Long dormitoryId) {
    this.dormitoryId = dormitoryId;
  }

  public Long getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Long operatorId) {
    this.operatorId = operatorId;
  }
}
