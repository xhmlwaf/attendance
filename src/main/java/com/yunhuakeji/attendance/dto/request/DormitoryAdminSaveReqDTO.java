package com.yunhuakeji.attendance.dto.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class DormitoryAdminSaveReqDTO {

  @ApiModelProperty(name = "用户楼栋关系列表列表", required = true)
  @NotNull(message = "用户楼栋关系列表列表不能为空")
  @Size(min = 1, max = 1000, message = "用户楼栋关系列表列表长度1-1000")
  @Valid
  private List<DormitoryAdminRelationDTO> refList;

  public List<DormitoryAdminRelationDTO> getRefList() {
    return refList;
  }

  public void setRefList(List<DormitoryAdminRelationDTO> refList) {
    this.refList = refList;
  }
}
