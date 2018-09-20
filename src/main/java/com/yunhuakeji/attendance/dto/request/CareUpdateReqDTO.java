package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class CareUpdateReqDTO {

  @ApiModelProperty(value = "关怀ID", required = true)
  @NotNull(message = "关怀ID不能为空")
  private Long careId;
  @ApiModelProperty(value = "备注", required = true)
  @Size(max = 50, message = "反馈结果长度不能超过50")
  private String remark;

  public Long getCareId() {
    return careId;
  }

  public void setCareId(Long careId) {
    this.careId = careId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
