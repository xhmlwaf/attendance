package com.yunhuakeji.attendance.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TimeClockStatusDTO {

  @ApiModelProperty(value = "打卡时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date clockDate;
  @ApiModelProperty(value = "打卡状态 状态枚举 1未打卡，2到勤，3晚归，4未归")
  private byte clockStatus;
  @ApiModelProperty(value = "年")
  private int year;
  @ApiModelProperty(value = "月")
  private int month;
  @ApiModelProperty(value = "日")
  private int day;

  public Date getClockDate() {
    return clockDate;
  }

  public void setClockDate(Date clockDate) {
    this.clockDate = clockDate;
  }

  public byte getClockStatus() {
    return clockStatus;
  }

  public void setClockStatus(byte clockStatus) {
    this.clockStatus = clockStatus;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }
}
