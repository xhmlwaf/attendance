package com.yunhuakeji.attendance.controller.admin;

import com.yunhuakeji.attendance.biz.SystemConfigBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.PasswordUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.ScreenConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.SysConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.TermSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.SysConfigRspDTO;
import com.yunhuakeji.attendance.dto.response.TermRspDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.List;

@Validated
@Api(value = "系统配置模块接口")
@RestController
public class SystemConfigController {

  @Autowired
  private SystemConfigBiz systemConfigBiz;

  @PutMapping("/system-config")
  @ApiOperation(value = "修改系统配置")
  public Result updateSysConfig(@Valid @RequestBody SysConfigReqDTO reqDTO) {
    return systemConfigBiz.updateSysConfig(reqDTO);
  }

  @GetMapping("/system-config")
  @ApiOperation(value = "获取系统配置")
  public Result<SysConfigRspDTO> getSysConfig() {
    return systemConfigBiz.getSysConfig();
  }

  @PutMapping("/screen-config")
  @ApiOperation(value = "修改大屏幕显示文本")
  public Result updateScreenConfig(@Valid @RequestBody ScreenConfigReqDTO reqDTO) {
    return systemConfigBiz.updateScreenConfig(reqDTO);
  }

  @GetMapping("/screen-config")
  @ApiOperation(value = "获取大屏幕显示文本")
  public Result<String> getScreenConfig() {
    return systemConfigBiz.getScreenConfig();
  }

  @GetMapping("/clock-day-list")
  @ApiOperation(value = "根据年月获取打卡日列表")
  public Result<List<Integer>> listDaysByYearAndMonth(
      @ApiParam(value = "年份", required = true)
      @RequestParam(name = "year")
      @NotNull(message = "年份不能为空")
      @Min(value = 1000, message = "不合法的年份")
      @Max(value = 9999, message = "不合法的年份")
          Integer year,
      @ApiParam(value = "月份", required = true)
      @RequestParam(name = "month")
      @NotNull(message = "月份不能为空")
      @Min(value = 1, message = "不合法的月份")
      @Max(value = 12, message = "不合法的月份")
          Integer month) {

    return systemConfigBiz.listDaysByYearAndMonth(year, month);
  }

  @GetMapping("/clock-day-list-from-curr")
  @ApiOperation(value = "获取从当月开始的所有打卡日期")
  public Result<List<Integer>> listClockDayFromCurr() {

    return systemConfigBiz.listClockDayFromCurr();
  }

  @PostMapping("/term")
  @ApiOperation(value = "学期新增")
  public Result termSave(@Valid @RequestBody TermSaveReqDTO reqDTO) {
    return systemConfigBiz.termSave(reqDTO);
  }

  @GetMapping("/term")
  @ApiOperation(value = "当前学期列表查询")
  public Result<List<TermRspDTO>> listTerm() {

    return systemConfigBiz.listTerm();
  }

  @PutMapping("/password")
  @ApiOperation(value = "修改密码")
  Result updatePwd(@Valid @RequestBody PasswordUpdateReqDTO reqDTO) {
    return systemConfigBiz.updatePwd(reqDTO);
  }

}
