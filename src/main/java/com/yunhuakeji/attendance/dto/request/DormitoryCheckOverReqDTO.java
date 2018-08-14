package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class DormitoryCheckOverReqDTO {

  @ApiModelProperty(value = "宿舍ID", required = true)
  @NotNull(message = "宿舍ID不能为空")
  private Long dormitoryId;
  @ApiModelProperty(value = "操作人ID", required = true)
  @NotNull(message = "操作人ID不能为空")
  private Long operatorId;
  @ApiModelProperty(value = "操作人名称", required = true)
  @NotBlank(message = "操作人名称不能为空")
  private String operatorName;

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

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }
}
