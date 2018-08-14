package com.yunhuakeji.attendance.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class StartCareReqDTO {

  @ApiModelProperty(value = "发起人ID", required = true)
  @NotNull(message = "发起人ID不能为空")
  private Long operatorId;

  @ApiModelProperty(value = "学生ID", required = true)
  @NotNull(message = "学生ID不能为空")
  private List<Long> studentIds;

  public Long getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Long operatorId) {
    this.operatorId = operatorId;
  }

  public List<Long> getStudentIds() {
    return studentIds;
  }

  public void setStudentIds(List<Long> studentIds) {
    this.studentIds = studentIds;
  }
}
