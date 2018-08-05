package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "USER_BUILDING_REF")
public class UserBuildingRef implements Serializable {
    @Column(name = "USER_ID")
    private BigDecimal userId;

    @Column(name = "BUILDING_ID")
    private BigDecimal buildingId;

    private static final long serialVersionUID = 1L;

    /**
     * @return USER_ID
     */
    public BigDecimal getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    /**
     * @return BUILDING_ID
     */
    public BigDecimal getBuildingId() {
        return buildingId;
    }

    /**
     * @param buildingId
     */
    public void setBuildingId(BigDecimal buildingId) {
        this.buildingId = buildingId;
    }
}