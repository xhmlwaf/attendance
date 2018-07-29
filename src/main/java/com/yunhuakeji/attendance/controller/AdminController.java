package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.controller.dto.request.AdminLoginReqDTO;
import com.yunhuakeji.attendance.controller.dto.request.PasswordUpdateReqDTO;
import com.yunhuakeji.attendance.controller.dto.response.AdminLoginRspDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Api(value = "管理员账号接口")
@Controller
public class AdminController {

    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public Result<AdminLoginRspDTO> login(AdminLoginReqDTO reqDTO) {
        return null;
    }

    @PutMapping("/password")
    @ApiOperation(value = "修改密码")
    public Result updatePwd(PasswordUpdateReqDTO reqDTO) {
        return null;
    }


}
