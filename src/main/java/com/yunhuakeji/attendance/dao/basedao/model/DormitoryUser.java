package com.yunhuakeji.attendance.dao.basedao.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "B_DORMITORY_USER")
public class DormitoryUser implements Serializable {
    @Column(name = "DORMITORY_ID")
    private Long dormitoryId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "BED_CODE")
    private String bedCode;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "STATE")
    private String state;

    @Column(name = "STATE_DATE")
    private Date stateDate;

    private static final long serialVersionUID = 1L;

    /**
     * @return DORMITORY_ID
     */
    public Long getDormitoryId() {
        return dormitoryId;
    }

    /**
     * @param dormitoryId
     */
    public void setDormitoryId(Long dormitoryId) {
        this.dormitoryId = dormitoryId;
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
     * @return BED_CODE
     */
    public String getBedCode() {
        return bedCode;
    }

    /**
     * @param bedCode
     */
    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
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
        DormitoryUser other = (DormitoryUser) that;
        return (this.getDormitoryId() == null ? other.getDormitoryId() == null : this.getDormitoryId().equals(other.getDormitoryId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getBedCode() == null ? other.getBedCode() == null : this.getBedCode().equals(other.getBedCode()))
            && (this.getCreatedDate() == null ? other.getCreatedDate() == null : this.getCreatedDate().equals(other.getCreatedDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getStateDate() == null ? other.getStateDate() == null : this.getStateDate().equals(other.getStateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDormitoryId() == null) ? 0 : getDormitoryId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getBedCode() == null) ? 0 : getBedCode().hashCode());
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
        sb.append(", dormitoryId=").append(dormitoryId);
        sb.append(", userId=").append(userId);
        sb.append(", bedCode=").append(bedCode);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", state=").append(state);
        sb.append(", stateDate=").append(stateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}