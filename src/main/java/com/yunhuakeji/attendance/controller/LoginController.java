package com.yunhuakeji.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.yunhuakeji.attendance.biz.LoginBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.AdminLoginReqDTO;
import com.yunhuakeji.attendance.dto.response.AdminLoginRspDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@Api(value = "通用登陆模块")
@RestController
public class LoginController {

  private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  private LoginBiz loginBiz;

  @PostMapping("/login")
  @ApiOperation(value = "通用登录接口")
  public Result<AdminLoginRspDTO> login(@Valid @RequestBody AdminLoginReqDTO reqDTO) {
    logger.info("params:" + JSON.toJSONString(reqDTO));
    return loginBiz.login(reqDTO);
  }

}
