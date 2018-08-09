package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.CollegeBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.InstructorQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.WeekInfoRspDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "基础数据查询接口")
@Controller
public class BaseDataQueryController {

  @GetMapping("/base-data/dormitory-building/all")
  @ApiOperation(value = "宿舍楼列表")
  public Result<List<BuildingQueryRspDTO>> listAllBuilding() {
    return null;
  }

  @GetMapping("/base-data/secondary-college/all")
  @ApiOperation(value = "二级学院")
  public Result<List<CollegeBaseInfoDTO>> listAllSecondaryCollege() {
    return null;
  }

  @GetMapping("/base-data/week-info/all")
  @ApiOperation(value = "周数信息")
  public Result<List<WeekInfoRspDTO>> listAllWeekInfo() {
    return null;
  }

  @GetMapping("/base-data/major-info/all")
  @ApiOperation(value = "专业信息")
  public Result<List<MajorInfo>> listAllMajorInfo(
      @ApiParam(name = "院系ID")
      @RequestParam(name = "orgId", required = false)
          Long orgId) {
    return null;
  }

  @GetMapping("/base-data/instructor-info/all")
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
