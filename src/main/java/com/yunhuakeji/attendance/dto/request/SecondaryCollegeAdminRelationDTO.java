package com.yunhuakeji.attendance.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class SecondaryCollegeAdminRelationDTO {

  @ApiModelProperty(name = "用户ID", required = true)
  @NotNull(message = "用户ID不能为空")
  private Long userId;

  @ApiModelProperty(name = "学院ID列表", required = true)
  @NotNull(message = "学院ID列表不能为空")
  @Size(min = 1, max = 1000, message = "学院ID列表长度1-1000")
  private List<Long> orgIdList;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public List<Long> getOrgIdList() {
    return orgIdList;
  }

  public void setOrgIdList(List<Long> orgIdList) {
    this.orgIdList = orgIdList;
  }
}
