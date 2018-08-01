package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.controller.dto.response.DormitoryBuildingQueryRspDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "宿舍楼接口")
@Controller
public class BuildingController {

  @PostMapping("/dormitory-building/all")
  @ApiOperation(value = "全部宿舍楼列表")
  public Result<List<DormitoryBuildingQueryRspDTO>> listDormitoryBuilding() {
    return null;
  }
}
