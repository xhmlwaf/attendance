package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class StudentClockUpdateReqDTO {

  @ApiModelProperty(value = "ID", required = true)
  @NotNull(message = "ID不能为空")
  private Long id;
  @ApiModelProperty(value = "状态", required = true)
  @NotNull(message = "状态不能为空")
  private Byte status;
  @ApiModelProperty(value = "备注", required = true)
  //@NotBlank(message = "备注不能为空")
  //@Size(max = 30, message = "备注长度不超过30")
  private String remark;
  @ApiModelProperty(value = "操作人ID", required = true)
  @NotNull(message = "操作人ID不能为空")
  private Long operatorId;
  @ApiModelProperty(value = "操作人名称", required = true)
  @NotNull(message = "操作人名称不能为空")
  private String operatorName;
  @ApiModelProperty(value = "操作应用类型 1.晚归查寝 2平台后台", required = true)
  @NotNull(message = "操作应用类型不能为空")
  private Byte appType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Long getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Long operatorId) {
    this.operatorId = operatorId;
  }

  public Byte getAppType() {
    return appType;
  }

  public void setAppType(Byte appType) {
    this.appType = appType;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }
}
