package com.yunhuakeji.attendance.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class TermSaveReqDTO {

  @ApiModelProperty(value = "学期", required = true)
  @NotNull(message = "学期不能为空,1-2")
  @Min(value = 1,message = "范围1-2")
  @Max(value = 2,message = "范围1-2")
  private Byte termNumber;
  @ApiModelProperty(value = "学期开始时间 格式yyyy-MM-dd", required = true)
  @NotBlank(message = "学期开始时间不能为空")
  private String startDate;
  @ApiModelProperty(value = "学期结束时间 格式yyyy-MM-dd", required = true)
  @NotBlank(message = "学期结束时间不能为空")
  private String endDate;
  @ApiModelProperty(value = "开始年", required = true)
  @NotNull(message = "开始年不能为空")
  @Min(value = 1000, message = "不合法的年份")
  @Max(value = 9999, message = "不合法的年份")
  private Integer startYear;
  @ApiModelProperty(value = "结束年", required = true)
  @NotNull(message = "结束年不能为空")
  @Min(value = 1000, message = "不合法的年份")
  @Max(value = 9999, message = "不合法的年份")
  private Integer endYear;

  public Byte getTermNumber() {
    return termNumber;
  }

  public void setTermNumber(Byte termNumber) {
    this.termNumber = termNumber;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

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
}
