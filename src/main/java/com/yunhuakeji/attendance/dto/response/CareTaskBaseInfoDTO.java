package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

public class CareTaskBaseInfoDTO extends StudentBaseInfoDTO{

  @ApiModelProperty(value = "关怀ID")
  private Long careId;
  @ApiModelProperty(value = "辅导员ID")
  private Long instructorId;
  @ApiModelProperty(value = "辅导员名称")
  private String instructorName;
  @ApiModelProperty(value = "任务时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date taskDate;
  @ApiModelProperty(value = "关怀状态")
  private Byte status;
  @ApiModelProperty(value = "处理时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date dealDate;
  @ApiModelProperty(value = "反馈结果")
  private String remark;

  public Long getCareId() {
    return careId;
  }

  public void setCareId(Long careId) {
    this.careId = careId;
  }

  public Long getInstructorId() {
    return instructorId;
  }

  public void setInstructorId(Long instructorId) {
    this.instructorId = instructorId;
  }

  public String getInstructorName() {
    return instructorName;
  }

  public void setInstructorName(String instructorName) {
    this.instructorName = instructorName;
  }

  public Date getTaskDate() {
    return taskDate;
  }

  public void setTaskDate(Date taskDate) {
    this.taskDate = taskDate;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Date getDealDate() {
    return dealDate;
  }

  public void setDealDate(Date dealDate) {
    this.dealDate = dealDate;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
