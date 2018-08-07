package com.yunhuakeji.attendance.dao.bizdao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CHECK_DORMITORY")
public class CheckDormitory {

  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = "DORMITORY_ID")
  private Long dormitoryId;

  @Column(name = "OPERATOR_ID")
  private Long operatorId;

  @Column(name = "OPERATOR_NAME")
  private String operatorName;

  @Column(name = "OPERATE_DATE")
  private Date operateDate;

  @Column(name = "STAT_DATE")
  private Long statDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getDormitoryId() {
    return dormitoryId;
  }

  public void setDormitoryId(Long dormitoryId) {
    this.dormitoryId = dormitoryId;
  }

  public Long getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Long operatorId) {
    this.operatorId = operatorId;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public Date getOperateDate() {
    return operateDate;
  }

  public void setOperateDate(Date operateDate) {
    this.operateDate = operateDate;
  }

  public Long getStatDate() {
    return statDate;
  }

  public void setStatDate(Long statDate) {
    this.statDate = statDate;
  }
}
