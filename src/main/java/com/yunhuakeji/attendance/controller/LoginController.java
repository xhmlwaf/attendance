package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.AdminLoginReqDTO;
import com.yunhuakeji.attendance.dto.response.AdminLoginRspDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "通用登陆模块")
@RestController
public class LoginController {

    @PostMapping("/login")
    @ApiOperation(value = "通用登录接口")
    public Result<AdminLoginRspDTO> login(@Valid @RequestBody AdminLoginReqDTO reqDTO) {

        return null;
    }

}
