package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class MajorQueryRspDTO {

  @ApiParam(name = "专业ID")
  private Long majorId;
  @ApiParam(name = "专业名称")
  private String majorName;

}
