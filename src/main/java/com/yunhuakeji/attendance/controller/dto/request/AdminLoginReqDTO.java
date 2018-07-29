package com.yunhuakeji.attendance.controller.dto.request;

import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotBlank;

/**
 * 管理员登录
 */
public class AdminLoginReqDTO {

    @ApiParam(name = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;
    @ApiParam(name = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;
}
