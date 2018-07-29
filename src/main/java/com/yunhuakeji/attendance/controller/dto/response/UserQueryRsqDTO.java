package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

public class UserQueryRsqDTO {

    @ApiParam(name = "用户ID")
    private String userId;
    @ApiParam(name = "用户名")
    private String userName;
    @ApiParam(name = "头像")
    private String profilePhoto;
}
