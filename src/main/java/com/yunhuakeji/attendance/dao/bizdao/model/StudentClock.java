package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "STUDENT_CLOCK")
public class StudentClock implements Serializable {
  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = "USER_ID")
  private Long userId;

  @Column(name = "BUILDING_ID")
  private Long buildingId;

  @Column(name = "ORG_ID")
  private Long orgId;

  @Column(name = "INSTRUCTOR_ID")
  private Long instructorId;

  @Column(name = "CLOCK_TIME")
  private Date clockTime;

  @Column(name = "CREATE_TIME")
  private Date createTime;

  @Column(name = "UPDATE_TIME")
  private Date updateTime;

  @Column(name = "GENDER")
  private Byte gender;

  @Column(name = "LAT")
  private BigDecimal lat;

  @Column(name = "LON")
  private BigDecimal lon;

  @Column(name = "CLOCK_DATE")
  private Long clockDate;

  @Column(name = "CLOCK_STATUS")
  private Byte clockStatus;

  @Column(name = "DEVICE_ID")
  private String deviceId;

  @Column(name = "MAJOR_ID")
  private Long majorId;

  @Column(name = "CLASS_ID")
  private Long classId;

  @Column(name = "OPERATOR_NAME")
  private String operatorName;

  @Column(name = "APP_NAME")
  private String appName;

  @Column(name = "OPERATOR_ID")
  private Long operatorId;

  private String remark;

  private static final long serialVersionUID = 1L;

  /**
   * @return ID
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return USER_ID
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * @param userId
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * @return ORG_ID
   */
  public Long getOrgId() {
    return orgId;
  }

  /**
   * @param orgId
   */
  public void setOrgId(Long orgId) {
    this.orgId = orgId;
  }

  /**
   * @return INSTRUCTOR_ID
   */
  public Long getInstructorId() {
    return instructorId;
  }

  /**
   * @param instructorId
   */
  public void setInstructorId(Long instructorId) {
    this.instructorId = instructorId;
  }

  /**
   * @return CLOCK_TIME
   */
  public Date getClockTime() {
    return clockTime;
  }

  /**
   * @param clockTime
   */
  public void setClockTime(Date clockTime) {
    this.clockTime = clockTime;
  }

  /**
   * @return CREATE_TIME
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * @param createTime
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * @return UPDATE_TIME
   */
  public Date getUpdateTime() {
    return updateTime;
  }

  /**
   * @param updateTime
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * @return GENDER
   */
  public Byte getGender() {
    return gender;
  }

  /**
   * @param gender
   */
  public void setGender(Byte gender) {
    this.gender = gender;
  }

  /**
   * @return LAT
   */
  public BigDecimal getLat() {
    return lat;
  }

  /**
   * @param lat
   */
  public void setLat(BigDecimal lat) {
    this.lat = lat;
  }

  /**
   * @return LON
   */
  public BigDecimal getLon() {
    return lon;
  }

  /**
   * @param lon
   */
  public void setLon(BigDecimal lon) {
    this.lon = lon;
  }

  /**
   * @return CLOCK_DATE
   */
  public Long getClockDate() {
    return clockDate;
  }

  /**
   * @param clockDate
   */
  public void setClockDate(Long clockDate) {
    this.clockDate = clockDate;
  }

  /**
   * @return CLOCK_STATUS
   */
  public Byte getClockStatus() {
    return clockStatus;
  }

  /**
   * @param clockStatus
   */
  public void setClockStatus(Byte clockStatus) {
    this.clockStatus = clockStatus;
  }

  /**
   * @return DEVICE_ID
   */
  public String getDeviceId() {
    return deviceId;
  }

  /**
   * @param deviceId
   */
  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  /**
   * @return MAJOR_ID
   */
  public Long getMajorId() {
    return majorId;
  }

  /**
   * @param majorId
   */
  public void setMajorId(Long majorId) {
    this.majorId = majorId;
  }

  public Long getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(Long buildingId) {
    this.buildingId = buildingId;
  }

  public Long getClassId() {
    return classId;
  }

  public void setClassId(Long classId) {
    this.classId = classId;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public Long getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Long operatorId) {
    this.operatorId = operatorId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}