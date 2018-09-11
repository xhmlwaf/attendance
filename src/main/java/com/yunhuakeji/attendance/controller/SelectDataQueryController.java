package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.biz.SelectDataQueryBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import java.util.List;

@Validated
@Api(value = "下拉数据查询接口")
@RestController
public class SelectDataQueryController {

  @Autowired
  private SelectDataQueryBiz selectDataQueryBiz;

  @GetMapping("/select-data/dormitory-building/all")
  @ApiOperation(value = "宿舍楼列表")
  public Result<List<BuildingQueryRspDTO>> listAllBuilding() {
    return selectDataQueryBiz.listAllBuilding();
  }

  @GetMapping("/select-data/secondary-college/all")
  @ApiOperation(value = "查询所有学院列表")
  public Result<List<CollegeBaseInfoDTO>> listAllSecondaryCollege() {
    return selectDataQueryBiz.listAllSecondaryCollege();
  }


  @GetMapping("/select-data/week-info/all")
  @ApiOperation(value = "周数信息")
  public Result<List<WeekInfoRspDTO>> listAllWeekInfo() {
    return selectDataQueryBiz.listAllWeekInfo();
  }

  @GetMapping("/select-data/major-info/all")
  @ApiOperation(value = "专业信息")
  public Result<List<MajorQueryRspDTO>> listAllMajorInfo(
      @ApiParam(value = "院系ID")
      @RequestParam(name = "orgId", required = false)
          Long orgId) {
    return selectDataQueryBiz.listAllMajorInfo(orgId);
  }

  @GetMapping("/select-data/instructor-info/all")
  @ApiOperation(value = "查询辅导员列表")
  public Result<List<InstructorQueryRspDTO>> listInstructor(
      @ApiParam(value = "院系ID")
      @RequestParam(name = "orgId", required = false)
          Long orgId,
      @ApiParam(value = "专业ID")
      @RequestParam(name = "majorId", required = false)
          Long majorId
  ) {

    return selectDataQueryBiz.listInstructor(orgId, majorId);
  }

  @GetMapping("/select-data/secondary-college/query-by-user")
  @ApiOperation(value = "根据用户ID查询学院列表 学生处用户返回全部，二级院系管理员返回对应的学院")
  public Result<List<CollegeBaseInfoDTO>> listByUserId(
      @ApiParam(value = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId) {
    return selectDataQueryBiz.listByUserId(userId);
  }

  @GetMapping("/select-data/user/{userId}")
  @ApiOperation(value = "根据用户ID查询基本信息")
  public Result<UserBaseInfoDTO> getUserBasicInfo(
      @ApiParam(value = "用户ID", required = true)
      @PathVariable("userId") Long userId) {
    return selectDataQueryBiz.getUserBasicInfo(userId);
  }

}
