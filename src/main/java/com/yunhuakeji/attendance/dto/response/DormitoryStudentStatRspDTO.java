package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class DormitoryStudentStatRspDTO {

  @ApiModelProperty(value = "学生ID")
  private Long studentId;

  @ApiModelProperty(value = "学生名称")
  private String studentName;

  @ApiModelProperty(value = "学生学号")
  private String studentCode;

  @ApiModelProperty(value = "头像")
  private String profilePhoto;

  @ApiModelProperty(value = "床号")
  private String bedCode;

  @ApiModelProperty(value = "打卡状态")
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
