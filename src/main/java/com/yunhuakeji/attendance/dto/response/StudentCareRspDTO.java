package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 可发起关怀信息
 */
public class StudentCareRspDTO extends StudentBaseInfoDTO {

  @ApiModelProperty(name = "任务发起时间")
  private Date taskCreateTime;

  @ApiModelProperty(name = "任务处理时间")
  private Date taskDealTime;

}
