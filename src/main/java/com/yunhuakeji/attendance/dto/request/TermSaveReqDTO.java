package com.yunhuakeji.attendance.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class TermSaveReqDTO {

  @ApiModelProperty(value = "学期", required = true)
  @Min(value = 1,message = "最小1")
  @Max(value = 2,message = "最大2")
  private Byte termNumber;
  @ApiModelProperty(value = "学期开始时间", required = true)
  @NotNull(message = "学期开始时间不能为空")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date startDate;
  @ApiModelProperty(value = "学期结束时间", required = true)
  @NotNull(message = "学期结束时间不能为空")
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
