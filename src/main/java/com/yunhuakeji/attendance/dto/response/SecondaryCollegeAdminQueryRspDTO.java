package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 二级学院管理员查询
 */
public class SecondaryCollegeAdminQueryRspDTO extends StaffBaseInfoDTO {



  @ApiParam(name = "学院ID")
  private String collegeId;
  @ApiParam(name = "学院名称")
  private String collegeName;

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
