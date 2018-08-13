package com.yunhuakeji.attendance.dto.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class TermRspDTO {

  @ApiModelProperty(value = "学期", required = true)
  private Byte termNumber;
  @ApiModelProperty(value = "学期开始时间", required = true)
  private Date startDate;
  @ApiModelProperty(value = "学期结束时间", required = true)
  private Date endDate;

  public Byte getTermNumber() {
    return termNumber;
  }

  public void setTermNumber(Byte termNumber) {
    this.termNumber = termNumber;
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
