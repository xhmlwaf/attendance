package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class AdminLoginRspDTO {

    @ApiParam(name = "登录token")
    private String token;
    @ApiParam(name = "管理员用户名")
    private String username;
    @ApiParam(name = "头像")
    private String profilePhoto;
}
