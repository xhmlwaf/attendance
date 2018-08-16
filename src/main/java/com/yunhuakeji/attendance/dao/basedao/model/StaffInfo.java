package com.yunhuakeji.attendance.dao.basedao.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "B_STAFF_INFO")
public class StaffInfo implements Serializable {
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "ENTRY_DATE")
    private Date entryDate;

    @Column(name = "ESTABLISHMENT_ID")
    private Byte establishmentId;

    @Column(name = "STAFF_TYPE_ID")
    private Integer staffTypeId;

    @Column(name = "CAMPUS_ID")
    private Long campusId;

    @Column(name = "QUALIFICATION_CODE")
    private String qualificationCode;

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
     * @return ENTRY_DATE
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * @param entryDate
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * @return ESTABLISHMENT_ID
     */
    public Byte getEstablishmentId() {
        return establishmentId;
    }

    /**
     * @param establishmentId
     */
    public void setEstablishmentId(Byte establishmentId) {
        this.establishmentId = establishmentId;
    }

    /**
     * @return STAFF_TYPE_ID
     */
    public Integer getStaffTypeId() {
        return staffTypeId;
    }

    /**
     * @param staffTypeId
     */
    public void setStaffTypeId(Integer staffTypeId) {
        this.staffTypeId = staffTypeId;
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
     * @return QUALIFICATION_CODE
     */
    public String getQualificationCode() {
        return qualificationCode;
    }

    /**
     * @param qualificationCode
     */
    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
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
        StaffInfo other = (StaffInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getEntryDate() == null ? other.getEntryDate() == null : this.getEntryDate().equals(other.getEntryDate()))
            && (this.getEstablishmentId() == null ? other.getEstablishmentId() == null : this.getEstablishmentId().equals(other.getEstablishmentId()))
            && (this.getStaffTypeId() == null ? other.getStaffTypeId() == null : this.getStaffTypeId().equals(other.getStaffTypeId()))
            && (this.getCampusId() == null ? other.getCampusId() == null : this.getCampusId().equals(other.getCampusId()))
            && (this.getQualificationCode() == null ? other.getQualificationCode() == null : this.getQualificationCode().equals(other.getQualificationCode()))
            && (this.getCreatedDate() == null ? other.getCreatedDate() == null : this.getCreatedDate().equals(other.getCreatedDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getStateDate() == null ? other.getStateDate() == null : this.getStateDate().equals(other.getStateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getEntryDate() == null) ? 0 : getEntryDate().hashCode());
        result = prime * result + ((getEstablishmentId() == null) ? 0 : getEstablishmentId().hashCode());
        result = prime * result + ((getStaffTypeId() == null) ? 0 : getStaffTypeId().hashCode());
        result = prime * result + ((getCampusId() == null) ? 0 : getCampusId().hashCode());
        result = prime * result + ((getQualificationCode() == null) ? 0 : getQualificationCode().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", entryDate=").append(entryDate);
        sb.append(", establishmentId=").append(establishmentId);
        sb.append(", staffTypeId=").append(staffTypeId);
        sb.append(", campusId=").append(campusId);
        sb.append(", qualificationCode=").append(qualificationCode);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", state=").append(state);
        sb.append(", stateDate=").append(stateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}