package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class UserQueryRsqDTO {

    @ApiModelProperty(name = "用户ID")
    private String userId;
    @ApiModelProperty(name = "用户名")
    private String userName;
    @ApiModelProperty(name = "头像")
    private String profilePhoto;
}
