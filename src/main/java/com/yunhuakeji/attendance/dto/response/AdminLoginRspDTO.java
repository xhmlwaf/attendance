package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class AdminLoginRspDTO {

    @ApiModelProperty(name = "登录token")
    private String token;
    @ApiModelProperty(name = "管理员用户名")
    private String username;
    @ApiModelProperty(name = "头像")
    private String profilePhoto;
}
