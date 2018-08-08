package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 大屏幕统计
 */
public class BigScreenMonitorStatRspDTO {

  @ApiParam(name = "晚归人数男")
  private Integer stayoutLateMaleNum;

  @ApiParam(name = "晚归人数女")
  private Integer stayoutLateFemaleNum;

  @ApiParam(name = "未归人数男")
  private Integer stayoutMaleNum;

  @ApiParam(name = "未归人数女")
  private Integer stayoutFemaleNum;

  @ApiParam(name = "总人数男")
  private Integer totalMaleNum;

  @ApiParam(name = "总人数女")
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
