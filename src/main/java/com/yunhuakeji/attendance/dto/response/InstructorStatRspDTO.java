package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class InstructorStatRspDTO extends InstructorBaseInfoDTO {

  @ApiModelProperty(value = "负责学生数")
  private int responsibleStudent;

  @ApiModelProperty(value = "打卡次数")
  private int clockCount;

  @ApiModelProperty(value = "处理关怀数")
  private int dealCareCount;

  @ApiModelProperty(value = "累计未归学生")
  private int totalLayOutCount;

  @ApiModelProperty(value = "累计晚归学生")
  private int totalLayOutLateCount;

  public int getResponsibleStudent() {
    return responsibleStudent;
  }

  public void setResponsibleStudent(int responsibleStudent) {
    this.responsibleStudent = responsibleStudent;
  }

  public int getClockCount() {
    return clockCount;
  }

  public void setClockCount(int clockCount) {
    this.clockCount = clockCount;
  }

  public int getDealCareCount() {
    return dealCareCount;
  }

  public void setDealCareCount(int dealCareCount) {
    this.dealCareCount = dealCareCount;
  }

  public int getTotalLayOutCount() {
    return totalLayOutCount;
  }

  public void setTotalLayOutCount(int totalLayOutCount) {
    this.totalLayOutCount = totalLayOutCount;
  }

  public int getTotalLayOutLateCount() {
    return totalLayOutLateCount;
  }

  public void setTotalLayOutLateCount(int totalLayOutLateCount) {
    this.totalLayOutLateCount = totalLayOutLateCount;
  }
}
