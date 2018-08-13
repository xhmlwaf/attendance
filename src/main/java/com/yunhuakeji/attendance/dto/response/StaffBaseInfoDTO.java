package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class StaffBaseInfoDTO {

  @ApiModelProperty(value = "ID")
  private Long userId;
  @ApiModelProperty(value = "名称")
  private String name;
  @ApiModelProperty(value = "工号")
  private String code;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
