package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class AdminLoginRspDTO {

    @ApiModelProperty(value = "登录token")
    private String token;
    @ApiModelProperty(value = "管理员用户名")
    private String username;
    @ApiModelProperty(value = "头像")
    private String profilePhoto;
}
