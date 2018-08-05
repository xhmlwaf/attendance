package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.AdminLoginReqDTO;
import com.yunhuakeji.attendance.dto.request.PasswordUpdateReqDTO;
import com.yunhuakeji.attendance.dto.response.AdminLoginRspDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Api(value = "通用登陆模块")
@Controller
public class LoginController {

    @PostMapping("/login")
    @ApiOperation(value = "通用登录接口")
    public Result<AdminLoginRspDTO> login(AdminLoginReqDTO reqDTO) {
        return null;
    }

}
