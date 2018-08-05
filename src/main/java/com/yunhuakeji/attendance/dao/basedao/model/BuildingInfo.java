package com.yunhuakeji.attendance.dao.basedao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "B_BUILDING_INFO")
public class BuildingInfo implements Serializable {
    @Id
    @Column(name = "BUILDING_ID")
    private Long buildingId;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private Short type;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TOTAL_FLOOR")
    private Short totalFloor;

    @Column(name = "UNIVERSITY_ID")
    private Integer universityId;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "STATE")
    private String state;

    @Column(name = "STATE_DATE")
    private Date stateDate;

    private static final long serialVersionUID = 1L;

    /**
     * @return BUILDING_ID
     */
    public Long getBuildingId() {
        return buildingId;
    }

    /**
     * @param buildingId
     */
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    /**
     * @return CODE
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
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
     * @return TYPE
     */
    public Short getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Short type) {
        this.type = type;
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
     * @return TOTAL_FLOOR
     */
    public Short getTotalFloor() {
        return totalFloor;
    }

    /**
     * @param totalFloor
     */
    public void setTotalFloor(Short totalFloor) {
        this.totalFloor = totalFloor;
    }

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
        BuildingInfo other = (BuildingInfo) that;
        return (this.getBuildingId() == null ? other.getBuildingId() == null : this.getBuildingId().equals(other.getBuildingId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getTotalFloor() == null ? other.getTotalFloor() == null : this.getTotalFloor().equals(other.getTotalFloor()))
            && (this.getUniversityId() == null ? other.getUniversityId() == null : this.getUniversityId().equals(other.getUniversityId()))
            && (this.getCreatedDate() == null ? other.getCreatedDate() == null : this.getCreatedDate().equals(other.getCreatedDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getStateDate() == null ? other.getStateDate() == null : this.getStateDate().equals(other.getStateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBuildingId() == null) ? 0 : getBuildingId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getTotalFloor() == null) ? 0 : getTotalFloor().hashCode());
        result = prime * result + ((getUniversityId() == null) ? 0 : getUniversityId().hashCode());
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
        sb.append(", buildingId=").append(buildingId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", address=").append(address);
        sb.append(", totalFloor=").append(totalFloor);
        sb.append(", universityId=").append(universityId);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", state=").append(state);
        sb.append(", stateDate=").append(stateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}