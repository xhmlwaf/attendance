package com.yunhuakeji.attendance.dto.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;

public class SecondaryCollegeAdminSaveReqDTO {

  @ApiParam(name = "二级管理员学院关系列表列表", required = true)
  @NotNull(message = "二级管理员学院关系列表列表不能为空")
  @Size(min = 1, max = 1000, message = "二级管理员学院关系列表列表长度1-1000")
  @Valid
  private List<SecondaryCollegeAdminRelationDTO> refList;

  public List<SecondaryCollegeAdminRelationDTO> getRefList() {
    return refList;
  }

  public void setRefList(List<SecondaryCollegeAdminRelationDTO> refList) {
    this.refList = refList;
  }
}
