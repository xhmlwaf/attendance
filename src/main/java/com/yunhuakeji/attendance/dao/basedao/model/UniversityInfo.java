package com.yunhuakeji.attendance.dao.basedao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "B_UNIVERSITY_INFO")
public class UniversityInfo implements Serializable {
    @Id
    @Column(name = "UNIVERSITY_ID")
    private Integer universityId;

    @Column(name = "UNIVERSITY_CODE")
    private String universityCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "STATE")
    private String state;

    @Column(name = "STATE_DATE")
    private Date stateDate;

    private static final long serialVersionUID = 1L;

    /**
     * @return UNIVERSITY_ID
     */
    public Integer getUniversityId() {
        return universityId;
    }

    /**
     * @param universityId
     */
    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    /**
     * @return UNIVERSITY_CODE
     */
    public String getUniversityCode() {
        return universityCode;
    }

    /**
     * @param universityCode
     */
    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
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
        UniversityInfo other = (UniversityInfo) that;
        return (this.getUniversityId() == null ? other.getUniversityId() == null : this.getUniversityId().equals(other.getUniversityId()))
            && (this.getUniversityCode() == null ? other.getUniversityCode() == null : this.getUniversityCode().equals(other.getUniversityCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getCreatedDate() == null ? other.getCreatedDate() == null : this.getCreatedDate().equals(other.getCreatedDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getStateDate() == null ? other.getStateDate() == null : this.getStateDate().equals(other.getStateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUniversityId() == null) ? 0 : getUniversityId().hashCode());
        result = prime * result + ((getUniversityCode() == null) ? 0 : getUniversityCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
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
        sb.append(", universityId=").append(universityId);
        sb.append(", universityCode=").append(universityCode);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", state=").append(state);
        sb.append(", stateDate=").append(stateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}