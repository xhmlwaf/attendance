package com.yunhuakeji.attendance.dao.basedao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "B_CLASS_INFO")
public class ClassInfo implements Serializable {
    @Id
    @Column(name = "CLASS_ID")
    private Long classId;

    @Column(name = "CLASS_CODE")
    private String classCode;

    @Column(name = "GRADE_ID")
    private Long gradeId;

    @Column(name = "CLASS_MANAGER_ID")
    private Long classManagerId;

    @Column(name = "CLASS_LEADER_ID")
    private Long classLeaderId;

    @Column(name = "INSTRUCTOR_ID")
    private Long instructorId;

    @Column(name = "MAJOR_ID")
    private Long majorId;

    @Column(name = "CAMPUS_ID")
    private Long campusId;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "STATE")
    private String state;

    @Column(name = "STATE_DATE")
    private Date stateDate;

    private static final long serialVersionUID = 1L;

    /**
     * @return CLASS_ID
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * @param classId
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * @return CLASS_CODE
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * @param classCode
     */
    public void setClassCode(String classCode) {
        this.classCode = classCode;
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
     * @return CLASS_MANAGER_ID
     */
    public Long getClassManagerId() {
        return classManagerId;
    }

    /**
     * @param classManagerId
     */
    public void setClassManagerId(Long classManagerId) {
        this.classManagerId = classManagerId;
    }

    /**
     * @return CLASS_LEADER_ID
     */
    public Long getClassLeaderId() {
        return classLeaderId;
    }

    /**
     * @param classLeaderId
     */
    public void setClassLeaderId(Long classLeaderId) {
        this.classLeaderId = classLeaderId;
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
     * @return CAMPUS_ID
     */
    public Long getCampusId() {
        return campusId;
    }

    /**
     * @param campusId
     */
    public void setCampusId(Long campusId) {
        this.campusId = campusId;
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

    /**
     * @return STATE
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ClassInfo other = (ClassInfo) that;
        return (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getClassCode() == null ? other.getClassCode() == null : this.getClassCode().equals(other.getClassCode()))
            && (this.getGradeId() == null ? other.getGradeId() == null : this.getGradeId().equals(other.getGradeId()))
            && (this.getClassManagerId() == null ? other.getClassManagerId() == null : this.getClassManagerId().equals(other.getClassManagerId()))
            && (this.getClassLeaderId() == null ? other.getClassLeaderId() == null : this.getClassLeaderId().equals(other.getClassLeaderId()))
            && (this.getInstructorId() == null ? other.getInstructorId() == null : this.getInstructorId().equals(other.getInstructorId()))
            && (this.getMajorId() == null ? other.getMajorId() == null : this.getMajorId().equals(other.getMajorId()))
            && (this.getCampusId() == null ? other.getCampusId() == null : this.getCampusId().equals(other.getCampusId()))
            && (this.getCreatedDate() == null ? other.getCreatedDate() == null : this.getCreatedDate().equals(other.getCreatedDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getStateDate() == null ? other.getStateDate() == null : this.getStateDate().equals(other.getStateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getClassCode() == null) ? 0 : getClassCode().hashCode());
        result = prime * result + ((getGradeId() == null) ? 0 : getGradeId().hashCode());
        result = prime * result + ((getClassManagerId() == null) ? 0 : getClassManagerId().hashCode());
        result = prime * result + ((getClassLeaderId() == null) ? 0 : getClassLeaderId().hashCode());
        result = prime * result + ((getInstructorId() == null) ? 0 : getInstructorId().hashCode());
        result = prime * result + ((getMajorId() == null) ? 0 : getMajorId().hashCode());
        result = prime * result + ((getCampusId() == null) ? 0 : getCampusId().hashCode());
        result = prime * result + ((getCreatedDate() == null) ? 0 : getCreatedDate().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getStateDate() == null) ? 0 : getStateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", classId=").append(classId);
        sb.append(", classCode=").append(classCode);
        sb.append(", gradeId=").append(gradeId);
        sb.append(", classManagerId=").append(classManagerId);
        sb.append(", classLeaderId=").append(classLeaderId);
        sb.append(", instructorId=").append(instructorId);
        sb.append(", majorId=").append(majorId);
        sb.append(", campusId=").append(campusId);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", state=").append(state);
        sb.append(", stateDate=").append(stateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}