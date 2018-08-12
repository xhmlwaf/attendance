package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class MajorQueryRspDTO {

  @ApiModelProperty(name = "专业ID")
  private Long majorId;
  @ApiModelProperty(name = "专业名称")
  private String majorName;

}
