package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiParam;

public class WeekInfoRspDTO {

  @ApiParam(name = "周数")
  private int weekNumber;
  @ApiParam(name = "开始时间")
  private Date startDate;
  @ApiParam(name = "结束时间")
  private Date endDate;

  public int getWeekNumber() {
    return weekNumber;
  }

  public void setWeekNumber(int weekNumber) {
    this.weekNumber = weekNumber;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}
