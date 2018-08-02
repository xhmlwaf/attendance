package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 辅导员基本信息
 */
public class InstructorBaseInfoDTO {

  @ApiParam(name = "辅导员ID")
  private Long userId;
  @ApiParam(name = "辅导员名称")
  private String name;
  @ApiParam(name = "辅导员工号")
  private String code;
  @ApiParam(name = "学院ID")
  private String collegeId;
  @ApiParam(name = "学院名称")
  private String collegeName;

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

  public String getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(String collegeId) {
    this.collegeId = collegeId;
  }

  public String getCollegeName() {
    return collegeName;
  }

  public void setCollegeName(String collegeName) {
    this.collegeName = collegeName;
  }
}
