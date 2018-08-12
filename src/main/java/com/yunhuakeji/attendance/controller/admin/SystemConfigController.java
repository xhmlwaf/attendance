package com.yunhuakeji.attendance.controller.admin;

import com.yunhuakeji.attendance.biz.SystemConfigBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.PasswordUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.ScreenConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.SysConfigReqDTO;
import com.yunhuakeji.attendance.dto.request.TermSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.SysConfigRspDTO;
import com.yunhuakeji.attendance.dto.response.TermRspDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "系统配置模块接口")
@Controller
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

    @GetMapping("/clock-day")
    @ApiOperation(value = "获取打卡日列表")
    public Result<List<Integer>> listDaysByYearAndMonth(
            @ApiParam(name = "年份", required = true)
            @RequestParam(name = "year")
            @NotNull(message = "年份不能为空")
            @Min(value = 1000, message = "不合法的年份")
            @Max(value = 9999, message = "不合法的年份")
                    Integer year,
            @ApiParam(name = "月份", required = true)
            @RequestParam(name = "month")
            @NotNull(message = "月份不能为空")
            @Min(value = 1, message = "不合法的月份")
            @Max(value = 12, message = "不合法的月份")
                    Integer month) {

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
    public Result<List<TermRspDTO>> listTerm() {
        return null;
    }

}
