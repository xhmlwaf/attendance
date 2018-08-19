package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 可发起关怀信息
 */
public class StudentCareRspDTO extends StudentBaseInfoDTO {

  @ApiModelProperty(value = "任务发起时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date taskCreateTime;

  @ApiModelProperty(value = "任务处理时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date taskDealTime;

  public Date getTaskCreateTime() {
    return taskCreateTime;
  }

  public void setTaskCreateTime(Date taskCreateTime) {
    this.taskCreateTime = taskCreateTime;
  }

  public Date getTaskDealTime() {
    return taskDealTime;
  }

  public void setTaskDealTime(Date taskDealTime) {
    this.taskDealTime = taskDealTime;
  }
}
