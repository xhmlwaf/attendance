package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class DormitoryCheckDayStatListRspDTO {

  @ApiModelProperty(value = "学生ID")
  private Long studentId;

  @ApiModelProperty(value = "学生名称")
  private String studentName;

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }
}
