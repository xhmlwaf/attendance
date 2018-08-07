package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class DormitoryStudentStatRspDTO {

  @ApiParam(name = "学生ID")
  private Long studentId;

  @ApiParam(name = "学生名称")
  private String studentName;

  @ApiParam(name = "学生学号")
  private String studentCode;

  @ApiParam(name = "头像")
  private String profilePhoto;

  @ApiParam(name = "床号")
  private String bedCode;

  @ApiParam(name = "打卡状态")
  private Byte clockStatus;

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

  public String getStudentCode() {
    return studentCode;
  }

  public void setStudentCode(String studentCode) {
    this.studentCode = studentCode;
  }

  public String getProfilePhoto() {
    return profilePhoto;
  }

  public void setProfilePhoto(String profilePhoto) {
    this.profilePhoto = profilePhoto;
  }

  public String getBedCode() {
    return bedCode;
  }

  public void setBedCode(String bedCode) {
    this.bedCode = bedCode;
  }

  public Byte getClockStatus() {
    return clockStatus;
  }

  public void setClockStatus(Byte clockStatus) {
    this.clockStatus = clockStatus;
  }
}
