package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;

public class StudentClockUpdateReqDTO {

  @ApiParam(name = "ID", required = true)
  @NotNull(message = "ID不能为空")
  private Long id;
  @ApiParam(name = "状态", required = true)
  @NotNull(message = "状态不能为空")
  private Byte status;
  @ApiParam(name = "备注", required = true)
  @NotBlank(message = "备注不能为空")
  @Size(max = 30, message = "备注长度不超过30")
  private String remark;
  @ApiParam(name = "操作人ID", required = true)
  @NotNull(message = "操作人ID不能为空")
  private Long operatorId;

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
}
