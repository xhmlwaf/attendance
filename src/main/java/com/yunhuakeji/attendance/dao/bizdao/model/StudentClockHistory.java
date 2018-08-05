package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "STUDENT_CLOCK_HISTORY")
public class StudentClockHistory implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "OPERATOR_ID")
    private Long operatorId;

    @Column(name = "OPERATOR_NAME")
    private String operatorName;

    @Column(name = "OPERATE_TIME")
    private Date operateTime;

    @Column(name = "APP_NAME")
    private String appName;

    @Column(name = "STAT_DATE")
    private Long statDate;

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
     * @return OPERATOR_ID
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * @return OPERATOR_NAME
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * @param operatorName
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * @return OPERATE_TIME
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * @param operateTime
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * @return APP_NAME
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * @return STAT_DATE
     */
    public Long getStatDate() {
        return statDate;
    }

    /**
     * @param statDate
     */
    public void setStatDate(Long statDate) {
        this.statDate = statDate;
    }
}