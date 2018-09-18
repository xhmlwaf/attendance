package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * 管理员登录
 */
public class AdminLoginReqDTO {

  @ApiModelProperty(value = "用户名", required = true)
  @NotBlank(message = "用户名不能为空")
  private String username;
  @ApiModelProperty(value = "密码", required = true)
  @NotBlank(message = "密码不能为空")
  private String password;
  @ApiModelProperty(value = "系统 1分析系统 2后台", required = true)
  @NotNull(message = "系统字段不能为空")
  @Min(value = 1, message = "范围1-2")
  @Max(value = 2, message = "范围1-2")
  private Integer sys;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getSys() {
    return sys;
  }

  public void setSys(Integer sys) {
    this.sys = sys;
  }
}
