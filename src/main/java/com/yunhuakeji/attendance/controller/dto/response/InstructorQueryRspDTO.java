package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

public class InstructorQueryRspDTO {

  @ApiParam(name = "辅导员ID")
  private Long instructorId;
  @ApiParam(name = "辅导员名称")
  private String instructorName;
}
