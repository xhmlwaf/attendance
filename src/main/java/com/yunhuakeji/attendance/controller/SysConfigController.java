package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.controller.dto.request.ScreenConfigReqDTO;
import com.yunhuakeji.attendance.controller.dto.request.SysConfigReqDTO;
import com.yunhuakeji.attendance.controller.dto.response.SysConfigRspDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Api(value = "系统配置操作接口")
@Controller
public class SysConfigController {

    @PutMapping("/sysconfig")
    @ApiOperation(value = "系统配置")
    public Result updateSysConfig(SysConfigReqDTO reqDTO) {
        return null;
    }

    @GetMapping("/sysconfig")
    @ApiOperation(value = "获取系统配置")
    public Result<SysConfigRspDTO> getSysConfig() {
        return null;
    }

    @PutMapping("/screen-config")
    @ApiOperation(value = "屏幕显示配置")
    public Result updateScreenConfig(ScreenConfigReqDTO reqDTO) {
        return null;
    }

}
