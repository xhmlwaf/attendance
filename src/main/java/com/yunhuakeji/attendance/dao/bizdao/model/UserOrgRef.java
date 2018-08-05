package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "USER_ORG_REF")
public class UserOrgRef implements Serializable {
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "ORG_ID")
    private Long orgId;

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
}