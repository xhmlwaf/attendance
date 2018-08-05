package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Care implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORG_ID")
    private Long orgId;

    @Column(name = "MAJOR_ID")
    private Long majorId;

    @Column(name = "INSTRUCTOR_ID")
    private Long instructorId;

    @Column(name = "ORIGINATOR_ID")
    private Long originatorId;

    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "ORIGINATE_TIME")
    private Date originateTime;

    @Column(name = "DEAL_TIME")
    private Date dealTime;

    @Column(name = "REMARK")
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
     * @return ORIGINATOR_ID
     */
    public Long getOriginatorId() {
        return originatorId;
    }

    /**
     * @param originatorId
     */
    public void setOriginatorId(Long originatorId) {
        this.originatorId = originatorId;
    }

    /**
     * @return STUDENT_ID
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * @param studentId
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    /**
     * @return ORIGINATE_TIME
     */
    public Date getOriginateTime() {
        return originateTime;
    }

    /**
     * @param originateTime
     */
    public void setOriginateTime(Date originateTime) {
        this.originateTime = originateTime;
    }

    /**
     * @return DEAL_TIME
     */
    public Date getDealTime() {
        return dealTime;
    }

    /**
     * @param dealTime
     */
    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}