package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class UserQueryRsqDTO {

    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "头像")
    private String profilePhoto;
}
