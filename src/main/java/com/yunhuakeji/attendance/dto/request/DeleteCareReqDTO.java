package com.yunhuakeji.attendance.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class DeleteCareReqDTO {

  @ApiModelProperty(value = "发起人ID", required = true)
  @NotNull(message = "发起人ID不能为空")
  private Long operatorId;

  @ApiModelProperty(value = "关怀ID", required = true)
  @NotNull(message = "关怀ID不能为空")
  private List<Long> careIds;

  public Long getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Long operatorId) {
    this.operatorId = operatorId;
  }

  public List<Long> getCareIds() {
    return careIds;
  }

  public void setCareIds(List<Long> careIds) {
    this.careIds = careIds;
  }
}
