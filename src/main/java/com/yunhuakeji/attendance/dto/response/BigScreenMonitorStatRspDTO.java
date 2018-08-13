package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 大屏幕统计
 */
public class BigScreenMonitorStatRspDTO {

  @ApiModelProperty(value = "晚归人数男")
  private Integer stayoutLateMaleNum;

  @ApiModelProperty(value = "晚归人数女")
  private Integer stayoutLateFemaleNum;

  @ApiModelProperty(value = "未归人数男")
  private Integer stayoutMaleNum;

  @ApiModelProperty(value = "未归人数女")
  private Integer stayoutFemaleNum;

  @ApiModelProperty(value = "总人数男")
  private Integer totalMaleNum;

  @ApiModelProperty(value = "总人数女")
  private Integer totalFemaleNum;

  public Integer getStayoutLateMaleNum() {
    return stayoutLateMaleNum;
  }

  public void setStayoutLateMaleNum(Integer stayoutLateMaleNum) {
    this.stayoutLateMaleNum = stayoutLateMaleNum;
  }

  public Integer getStayoutLateFemaleNum() {
    return stayoutLateFemaleNum;
  }

  public void setStayoutLateFemaleNum(Integer stayoutLateFemaleNum) {
    this.stayoutLateFemaleNum = stayoutLateFemaleNum;
  }

  public Integer getStayoutMaleNum() {
    return stayoutMaleNum;
  }

  public void setStayoutMaleNum(Integer stayoutMaleNum) {
    this.stayoutMaleNum = stayoutMaleNum;
  }

  public Integer getStayoutFemaleNum() {
    return stayoutFemaleNum;
  }

  public void setStayoutFemaleNum(Integer stayoutFemaleNum) {
    this.stayoutFemaleNum = stayoutFemaleNum;
  }

  public Integer getTotalMaleNum() {
    return totalMaleNum;
  }

  public void setTotalMaleNum(Integer totalMaleNum) {
    this.totalMaleNum = totalMaleNum;
  }

  public Integer getTotalFemaleNum() {
    return totalFemaleNum;
  }

  public void setTotalFemaleNum(Integer totalFemaleNum) {
    this.totalFemaleNum = totalFemaleNum;
  }
}
