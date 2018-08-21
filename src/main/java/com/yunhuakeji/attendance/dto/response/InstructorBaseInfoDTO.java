package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 辅导员基本信息
 */
public class InstructorBaseInfoDTO {

  @ApiModelProperty(value = "辅导员ID")
  private Long userId;
  @ApiModelProperty(value = "辅导员名称")
  private String name;
  @ApiModelProperty(value = "辅导员工号")
  private String code;
  @ApiModelProperty(value = "学院ID")
  private Long collegeId;
  @ApiModelProperty(value = "学院名称")
  private String collegeName;
  @ApiModelProperty(value = "班级ID")
  private Long classId;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(Long collegeId) {
    this.collegeId = collegeId;
  }

  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }

  public Long getClassId() {
    return classId;
  }

  public void setClassId(Long classId) {
    this.classId = classId;
  }
}
