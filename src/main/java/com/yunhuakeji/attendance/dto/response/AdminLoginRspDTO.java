package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class AdminLoginRspDTO extends UserBaseInfoDTO{

    @ApiModelProperty(value = "token")
    private String token;

    private String orgName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
