package com.yunhuakeji.attendance.dao.basedao.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "B_STUDENT_INFO")
public class StudentInfo implements Serializable {
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "LEVEL_ID")
    private Byte levelId;

    @Column(name = "AT_SCHOOL")
    private Byte atSchool;

    @Column(name = "ENTRANCE_DATE")
    private Date entranceDate;

    @Column(name = "GRADE_ID")
    private Long gradeId;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "STATE")
    private String state;

    @Column(name = "STATE_DATE")
    private Date stateDate;

    private static final long serialVersionUID = 1L;

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
     * @return LEVEL_ID
     */
    public Byte getLevelId() {
        return levelId;
    }

    /**
     * @param levelId
     */
    public void setLevelId(Byte levelId) {
        this.levelId = levelId;
    }

    /**
     * @return AT_SCHOOL
     */
    public Byte getAtSchool() {
        return atSchool;
    }

    /**
     * @param atSchool
     */
    public void setAtSchool(Byte atSchool) {
        this.atSchool = atSchool;
    }

    /**
     * @return ENTRANCE_DATE
     */
    public Date getEntranceDate() {
        return entranceDate;
    }

    /**
     * @param entranceDate
     */
    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }

    /**
     * @return GRADE_ID
     */
    public Long getGradeId() {
        return gradeId;
    }

    /**
     * @param gradeId
     */
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * @return CREATED_DATE
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return STATE_DATE
     */
    public Date getStateDate() {
        return stateDate;
    }

    /**
     * @param stateDate
     */
    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

}