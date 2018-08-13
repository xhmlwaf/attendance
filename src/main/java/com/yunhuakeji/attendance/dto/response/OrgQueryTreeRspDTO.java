package com.yunhuakeji.attendance.dto.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class OrgQueryTreeRspDTO {

  @ApiModelProperty(value = "机构列表")
  private List<OrgBaseInfoDTO> orgBaseInfoDTOList;

  public List<OrgBaseInfoDTO> getOrgBaseInfoDTOList() {
    return orgBaseInfoDTOList;
  }

  public void setOrgBaseInfoDTOList(List<OrgBaseInfoDTO> orgBaseInfoDTOList) {
    this.orgBaseInfoDTOList = orgBaseInfoDTOList;
  }
}
