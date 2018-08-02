package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.DormitoryBuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryQueryRspDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "宿舍管理操作接口")
@Controller
public class DormitoryController {

  @PostMapping("/dormitory-building/all")
  @ApiOperation(value = "全部宿舍楼列表")
  public Result<List<DormitoryBuildingQueryRspDTO>> listDormitoryBuilding() {
    return null;
  }

  @GetMapping("/dormitory")
  @ApiOperation(value = "根据楼栋ID和层数（层数可为空）查询宿舍列表")
  public Result<DormitoryQueryRspDTO> listDormByFloorId(
      @ApiParam(name = "楼栋ID", required = true)
      @RequestParam(name = "buildingId")
      @NotNull(message = "楼栋ID不能为空")
          Long buildingId,
      @ApiParam(name = "层数")
      @RequestParam(name = "floorNumber", required = false)
      @NotNull(message = "层数不能为空")
          Integer floorNumber
  ) {
    return null;
  }
}
