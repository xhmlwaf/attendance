package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.MajorQueryRspDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "专业操作接口")
@Controller
public class MajorController {

  @GetMapping("/major")
  @ApiOperation(value = "根据院系ID（院系ID可为空，查全部）查询专业列表")
  public Result<List<MajorQueryRspDTO>> listMajor(
      @ApiParam(name = "院系ID")
      @RequestParam(name = "orgId",required = false)
          Long orgId
  ){

    return null;
  }
}
