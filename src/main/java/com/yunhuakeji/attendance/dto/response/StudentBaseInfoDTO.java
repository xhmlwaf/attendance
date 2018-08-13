package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 学生基础信息
 */
public class StudentBaseInfoDTO {

  @ApiModelProperty(value = "学生ID")
  private Long studentId;

  @ApiModelProperty(value = "学生名称")
  private String studentName;

  @ApiModelProperty(value = "学生学号")
  private String studentCode;

  @ApiModelProperty(value = "头像")
  private String profilePhoto;

  @ApiModelProperty(value = "学院ID")
  private Long collegeId;

  @ApiModelProperty(value = "学院名称")
  private String collegeName;

  @ApiModelProperty(value = "专业ID")
  private Long majorId;

  @ApiModelProperty(value = "专业名称")
  private String majorName;

  @ApiModelProperty(value = "班级ID")
  private Long classId;

  @ApiModelProperty(value = "班级名称")
  private String className;

  @ApiModelProperty(value = "辅导员ID")
  private Long instructorId;

  @ApiModelProperty(value = "辅导员名称")
  private String instructorName;

  @ApiModelProperty(value = "寝室号ID")
  private Long dormitoryId;

  @ApiModelProperty(value = "寝室名称")
  private String dormitoryName;

  @ApiModelProperty(value = "楼栋ID")
  private Long buildingId;

  @ApiModelProperty(value = "楼栋名称")
  private String buildingName;

  @ApiModelProperty(value = "床号")
  private String bedCode;

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

  public Long getMajorId() {
    return majorId;
  }

  public void setMajorId(Long majorId) {
    this.majorId = majorId;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  public Long getClassId() {
    return classId;
  }

  public void setClassId(Long classId) {
    this.classId = classId;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
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

  public Long getDormitoryId() {
    return dormitoryId;
  }

  public void setDormitoryId(Long dormitoryId) {
    this.dormitoryId = dormitoryId;
  }

  public String getDormitoryName() {
    return dormitoryName;
  }

  public void setDormitoryName(String dormitoryName) {
    this.dormitoryName = dormitoryName;
  }

  public Long getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(Long buildingId) {
    this.buildingId = buildingId;
  }

  public String getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

  public String getBedCode() {
    return bedCode;
  }

  public void setBedCode(String bedCode) {
    this.bedCode = bedCode;
  }
}
