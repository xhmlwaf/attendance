package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.biz.DormitoryBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockDetailStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitorySimpleRspDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "宿舍管理操作接口")
@Controller
public class DormitoryController {

  @Autowired
  private DormitoryBiz dormitoryBiz;

  @PostMapping("/dormitory-building/all")
  @ApiOperation(value = "全部宿舍楼列表")
  public Result<List<BuildingQueryRspDTO>> listAllBuilding() {
    return dormitoryBiz.listAllBuilding();
  }

  @GetMapping("/dormitory/all")
  @ApiOperation(value = "根据楼栋ID和层数（层数可为空）查询宿舍列表")
  public Result<List<DormitorySimpleRspDTO>> listDormitory(
      @ApiParam(name = "楼栋ID", required = true)
      @RequestParam(name = "buildingId")
      @NotNull(message = "楼栋ID不能为空")
          Long buildingId,
      @ApiParam(name = "层数")
      @RequestParam(name = "floorNumber", required = false)
      @NotNull(message = "层数不能为空")
          Integer floorNumber
  ) {
    return dormitoryBiz.listDormitory(buildingId, floorNumber);
  }

  @GetMapping("/dormitory-building/all/app")
  @ApiOperation(value = "根据用户查询宿舍楼")
  public Result<List<BuildingQueryRspDTO>> listBuildingForApp(
      @ApiParam(name = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(name = "app类型 1辅导员/2宿舍员/3学生处", required = true)
      @RequestParam(name = "appType")
      @Min(value = 1, message = "App类型 1辅导员/2宿舍员/3学生处")
      @Max(value = 3, message = "App类型 1辅导员/2宿舍员/3学生处")
      @NotNull(message = "app类型")
          Byte appType
  ) {

    return dormitoryBiz.listBuildingForApp(userId,appType);
  }

  @GetMapping("/dormitory/all/app")
  @ApiOperation(value = "根据用户查询宿舍楼下的宿舍")
  public Result<List<DormitorySimpleRspDTO>> listDormitoryForApp(
      @ApiParam(name = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(name = "app类型 1辅导员/2宿舍员/3学生处", required = true)
      @RequestParam(name = "appType")
      @Min(value = 1, message = "App类型 1辅导员/2宿舍员/3学生处")
      @Max(value = 3, message = "App类型 1辅导员/2宿舍员/3学生处")
      @NotNull(message = "app类型")
          Byte appType,
      @ApiParam(name = "楼栋ID", required = true)
      @RequestParam(name = "buildingId")
      @NotNull(message = "楼栋ID不能为空")
          Long buildingId,
      @ApiParam(name = "楼层", required = true)
      @RequestParam(name = "floorNumber")
      @NotNull(message = "楼层")
          Integer floorNumber

  ) {


    return dormitoryBiz.listDormitoryForApp(userId, appType, buildingId, floorNumber);
  }

  @GetMapping("/dormitory/all/app")
  @ApiOperation(value = "根据用户查询宿舍楼下的宿舍")
  public Result<List<DormitoryClockStatDTO>> listDormitoryClockStatForApp(
      @ApiParam(name = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(name = "app类型 1辅导员/2宿舍员/3学生处", required = true)
      @RequestParam(name = "appType")
      @Min(value = 1, message = "App类型 1辅导员/2宿舍员/3学生处")
      @Max(value = 3, message = "App类型 1辅导员/2宿舍员/3学生处")
      @NotNull(message = "app类型")
          Byte appType,
      @ApiParam(name = "楼栋ID", required = true)
      @RequestParam(name = "buildingId")
      @NotNull(message = "楼栋ID不能为空")
          Long buildingId,
      @ApiParam(name = "楼层")
      @RequestParam(name = "floorNumber", required = false)
          Integer floorNumber,
      @ApiParam(name = "宿舍ID")
      @RequestParam(name = "dormitoryId", required = false)
          Long dormitoryId

  ) {

    return dormitoryBiz.listDormitoryClockStatForApp(userId, appType, buildingId, floorNumber, dormitoryId);
  }

  @GetMapping("/dormitory/{dormitoryId}/detail/app")
  @ApiOperation(value = "根据用户查询宿舍楼下的宿舍")
  public Result<DormitoryClockDetailStatDTO> getDormitoryClockDetailStatForApp(
      @ApiParam(name = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(name = "app类型 1辅导员/2宿舍员/3学生处", required = true)
      @RequestParam(name = "appType")
      @Min(value = 1, message = "App类型 1辅导员/2宿舍员/3学生处")
      @Max(value = 3, message = "App类型 1辅导员/2宿舍员/3学生处")
      @NotNull(message = "app类型")
          Byte appType,
      @ApiParam(name = "宿舍ID", required = true)
      @PathVariable(name = "dormitoryId")
          Long dormitoryId

  ) {

    return null;
  }

}
