package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class InstructorQueryRspDTO {

  @ApiParam(name = "辅导员ID")
  private Long userId;
  @ApiParam(name = "辅导员名称")
  private String name;

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
}
