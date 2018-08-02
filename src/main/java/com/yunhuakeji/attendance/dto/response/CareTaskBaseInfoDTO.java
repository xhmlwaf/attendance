package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiParam;

public class CareTaskBaseInfoDTO {

  @ApiParam(name = "关怀ID")
  private Long careId;
  @ApiParam(name = "学生ID")
  private String studentId;
  @ApiParam(name = "学生姓名")
  private String studentName;
  @ApiParam(name = "辅导员ID")
  private Long instructorId;
  @ApiParam(name = "辅导员名称")
  private String instructorName;
  @ApiParam(name = "任务时间")
  private Date taskDate;
  @ApiParam(name = "关怀状态")
  private Byte status;
  @ApiParam(name = "处理时间")
  private Date dealDate;
  @ApiParam(name = "反馈结果")
  private String remark;

  public Long getCareId() {
    return careId;
  }

  public void setCareId(Long careId) {
    this.careId = careId;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
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
