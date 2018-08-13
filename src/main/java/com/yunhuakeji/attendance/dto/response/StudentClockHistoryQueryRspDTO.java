package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 打卡历史查询
 */
public class StudentClockHistoryQueryRspDTO {

  @ApiModelProperty(value = "操作时间")
  private Date operateTime;
  @ApiModelProperty(value = "应用名称")
  private String appName;
  @ApiModelProperty(value = "操作人ID")
  private Long operatorId;
  @ApiModelProperty(value = "操作人名称")
  private String operatorName;
  @ApiModelProperty(value = "状态")
  private int clockStatus;

  public Date getOperateTime() {
    return operateTime;
  }

  public void setOperateTime(Date operateTime) {
    this.operateTime = operateTime;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public Long getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Long operatorId) {
    this.operatorId = operatorId;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public int getClockStatus() {
    return clockStatus;
  }

  public void setClockStatus(int clockStatus) {
    this.clockStatus = clockStatus;
  }
}
