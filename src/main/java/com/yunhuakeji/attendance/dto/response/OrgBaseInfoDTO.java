package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class OrgBaseInfoDTO {

  @ApiParam(name = "机构ID")
  private Long orgId;

  @ApiParam(name = "父机构ID")
  private Long parentOrgId;

  @ApiParam(name = "机构名称")
  private String orgName;

  public Long getOrgId() {
    return orgId;
  }

  public void setOrgId(Long orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public Long getParentOrgId() {
    return parentOrgId;
  }

  public void setParentOrgId(Long parentOrgId) {
    this.parentOrgId = parentOrgId;
  }
}
