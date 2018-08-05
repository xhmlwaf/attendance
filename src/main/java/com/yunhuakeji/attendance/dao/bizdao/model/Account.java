package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Account implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "ROLE_TYPE")
    private Short roleType;

    @Column(name = "PASSWORD")
    private String password;

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
     * @return ROLE_TYPE
     */
    public Short getRoleType() {
        return roleType;
    }

    /**
     * @param roleType
     */
    public void setRoleType(Short roleType) {
        this.roleType = roleType;
    }

    /**
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}