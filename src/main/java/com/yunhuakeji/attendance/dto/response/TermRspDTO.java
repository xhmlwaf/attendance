package com.yunhuakeji.attendance.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class TermRspDTO {

  @ApiModelProperty(value = "开始年", required = true)
  private int startYear;
  @ApiModelProperty(value = "结束年", required = true)
  private int endYear;

  @ApiModelProperty(value = "学期1开始时间", required = true)
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date termOneStartDate;
  @ApiModelProperty(value = "学期1结束时间", required = true)
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date termOneEndDate;

  @ApiModelProperty(value = "学期2开始时间", required = true)
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date termTwoStartDate;
  @ApiModelProperty(value = "学期2结束时间", required = true)
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date termTwoEndDate;

  public int getStartYear() {
    return startYear;
  }

  public void setStartYear(int startYear) {
    this.startYear = startYear;
  }

  public int getEndYear() {
    return endYear;
  }

  public void setEndYear(int endYear) {
    this.endYear = endYear;
  }

  public Date getTermOneStartDate() {
    return termOneStartDate;
  }

  public void setTermOneStartDate(Date termOneStartDate) {
    this.termOneStartDate = termOneStartDate;
  }

  public Date getTermOneEndDate() {
    return termOneEndDate;
  }

  public void setTermOneEndDate(Date termOneEndDate) {
    this.termOneEndDate = termOneEndDate;
  }

  public Date getTermTwoStartDate() {
    return termTwoStartDate;
  }

  public void setTermTwoStartDate(Date termTwoStartDate) {
    this.termTwoStartDate = termTwoStartDate;
  }

  public Date getTermTwoEndDate() {
    return termTwoEndDate;
  }

  public void setTermTwoEndDate(Date termTwoEndDate) {
    this.termTwoEndDate = termTwoEndDate;
  }
}
