package com.yunhuakeji.attendance.controller.admin;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.PasswordUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.ScreenConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.SysConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.TermSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.SysConfigRspDTO;
import com.yunhuakeji.attendance.dto.response.TermRspDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "系统配置模块接口")
@Controller
public class SystemConfigController {

  @PutMapping("/system-config")
  @ApiOperation(value = "修改系统配置")
  public Result updateSysConfig(@Valid @RequestBody SysConfigReqDTO reqDTO) {
    return null;
  }

  @GetMapping("/system-config")
  @ApiOperation(value = "获取系统配置")
  public Result<SysConfigRspDTO> getSysConfig() {
    return null;
  }

  @PutMapping("/screen-config")
  @ApiOperation(value = "修改大屏幕显示文本")
  public Result updateScreenConfig(@Valid @RequestBody ScreenConfigReqDTO reqDTO) {
    return null;
  }

  @GetMapping("/screen-config")
  @ApiOperation(value = "获取大屏幕显示文本")
  public Result getScreenConfig(@Valid @RequestBody ScreenConfigReqDTO reqDTO) {
    return null;
  }

  @PutMapping("/password")
  @ApiOperation(value = "管理员修改密码")
  public Result updatePwd(@Valid @RequestBody PasswordUpdateReqDTO reqDTO) {
    return null;
  }

  @PostMapping("/term")
  @ApiOperation(value = "学期新增")
  public Result termSave(@Valid @RequestBody TermSaveReqDTO reqDTO) {
    return null;
  }

  @GetMapping("/term")
  @ApiOperation(value = "当前学期列表查询")
  public Result<TermRspDTO> listTerm() {
    return null;
  }

}
