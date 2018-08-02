package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class StaffBaseInfoDTO {

  @ApiParam(name = "ID")
  private Long userId;
  @ApiParam(name = "名称")
  private String name;
  @ApiParam(name = "工号")
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
