package com.yunhuakeji.attendance.dto.request;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class StudentClockBatchUpdateReqDTO {

  @ApiModelProperty(value = "操作人ID", required = true)
  private Long operatorId;
  @ApiModelProperty(value = "操作人名称", required = true)
  private String operatorName;
  @ApiModelProperty(value = "操作应用类型 1.晚归查寝 2平台后台", required = true)
  @NotNull(message = "操作应用类型不能为空")
  @Min(value = 1, message = "范围1-2")
  @Max(value = 2, message = "范围1-2")
  private Byte appType;

  @NotEmpty(message = "更新列表不能为空")
  @Valid
  private List<UpdateClockDTO> updateClockDTOList;

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

  public Byte getAppType() {
    return appType;
  }

  public void setAppType(Byte appType) {
    this.appType = appType;
  }

  public List<UpdateClockDTO> getUpdateClockDTOList() {
    return updateClockDTOList;
  }

  public void setUpdateClockDTOList(List<UpdateClockDTO> updateClockDTOList) {
    this.updateClockDTOList = updateClockDTOList;
  }
}
