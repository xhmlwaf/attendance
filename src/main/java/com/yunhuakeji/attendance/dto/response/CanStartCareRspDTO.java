package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 可发起关怀信息
 */
public class CanStartCareRspDTO extends StudentBaseInfoDTO {

  @ApiModelProperty(value = "连续晚归次数")
  private int continuousStayoutLateDays;

  @ApiModelProperty(value = "连续未归次数")
  private int continuousStayoutDays;

  @ApiModelProperty(value = "累计被关怀次数")
  private int totalCared;

  public int getContinuousStayoutLateDays() {
    return continuousStayoutLateDays;
  }

  public void setContinuousStayoutLateDays(int continuousStayoutLateDays) {
    this.continuousStayoutLateDays = continuousStayoutLateDays;
  }

  public int getContinuousStayoutDays() {
    return continuousStayoutDays;
  }

  public void setContinuousStayoutDays(int continuousStayoutDays) {
    this.continuousStayoutDays = continuousStayoutDays;
  }

  public int getTotalCared() {
    return totalCared;
  }

  public void setTotalCared(int totalCared) {
    this.totalCared = totalCared;
  }
}
