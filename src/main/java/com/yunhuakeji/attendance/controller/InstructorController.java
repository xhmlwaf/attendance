package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.controller.dto.response.InstructorQueryRspDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "辅导员接口")
@Controller
public class InstructorController {

  @GetMapping("/major")
  @ApiOperation(value = "查询辅导员列表")
  public Result<List<InstructorQueryRspDTO>> listInstructor(
      @ApiParam(name = "院系ID")
      @RequestParam(name = "orgId", required = false)
          Long orgId,
      @ApiParam(name = "专业ID")
      @RequestParam(name = "majorId", required = false)
          Long majorId
  ) {

    return null;
  }
}
