package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class DormitoryCheckWeekStatListRspDTO {

  @ApiModelProperty(value = "学生ID")
  private Long studentId;

  @ApiModelProperty(value = "学生名称")
  private String studentName;

  @ApiModelProperty(value = "次数")
  private int count;

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

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
