package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.controller.dto.response.MajorQueryRspDTO;

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
  @ApiOperation(value = "根据楼栋ID和层数（层数可为空）查询宿舍列表")
  public Result<List<MajorQueryRspDTO>> listMajor(
      @ApiParam(name = "院系ID")
      @RequestParam(name = "orgId",required = false)
          Long orgId
  ){

    return null;
  }
}
