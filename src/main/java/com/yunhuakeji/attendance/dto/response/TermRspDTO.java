package com.yunhuakeji.attendance.dto.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiParam;

public class TermRspDTO {

  @ApiParam(name = "学期", required = true)
  @Min(value = 1,message = "最小1")
  @Max(value = 2,message = "最大2")
  private Byte termNumber;
  @ApiParam(name = "学期开始时间", required = true)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date startDate;
  @ApiParam(name = "学期结束时间", required = true)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
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
