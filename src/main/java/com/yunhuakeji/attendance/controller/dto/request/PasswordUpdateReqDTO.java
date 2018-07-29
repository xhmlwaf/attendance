package com.yunhuakeji.attendance.controller.dto.request;

import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码请求
 */
public class PasswordUpdateReqDTO {

    @ApiParam(name = "管理员ID", required = true)
    @NotBlank(message = "管理员ID不能为空")
    private String adminid;
    @ApiParam(name = "旧密码", required = true)
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @ApiParam(name = "新密码", required = true)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
