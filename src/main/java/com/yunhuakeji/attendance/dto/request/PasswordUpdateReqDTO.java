package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;

/**
 * 修改密码请求
 */
public class PasswordUpdateReqDTO {

    @ApiParam(name = "token", required = true)
    @NotBlank(message = "token不能为空")
    private String token;
    @ApiParam(name = "旧密码", required = true)
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @ApiParam(name = "新密码", required = true)
    @NotBlank(message = "新密码不能为空")
    @Size(min = 6,max = 18,message = "密码长度在6-18之间")
    private String newPassword;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
