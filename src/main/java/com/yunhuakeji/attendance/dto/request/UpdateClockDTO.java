package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class UpdateClockDTO {

  @ApiModelProperty(value = "ID", required = true)
  @NotNull(message = "ID不能为空")
  private Long studentId;
  @ApiModelProperty(value = "状态", required = true)
  @NotNull(message = "状态不能为空")
  private Byte status;
  @ApiModelProperty(value = "备注")
  @Size(max = 50, message = "长度不能超过50")
  private String remark;

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
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
}
