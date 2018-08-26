package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.biz.DormitoryBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.DormitoryCheckOverReqDTO;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckDayStatListRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckDayStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckWeekStatListRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckWeekStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockDetailStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitorySimpleRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryStudentStatRspDTO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentDormitoryRsqDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "查寝操作接口")
@RestController
public class DormitoryCheckController {

  @Autowired
  private DormitoryBiz dormitoryBiz;

  @GetMapping("/dormitory/all")
  @ApiOperation(value = "根据楼栋ID和层数（层数可为空）查询宿舍列表")
  public Result<List<DormitorySimpleRspDTO>> listDormitory(
      @ApiParam(value = "楼栋ID", required = true)
      @RequestParam(name = "buildingId")
      @NotNull(message = "楼栋ID不能为空")
          Long buildingId,
      @ApiParam(value = "层数")
      @RequestParam(name = "floorNumber", required = false)
      @NotNull(message = "层数不能为空")
          Integer floorNumber
  ) {
    return dormitoryBiz.listDormitory(buildingId, floorNumber);
  }

  @GetMapping("/dormitory-building/query-by-user")
  @ApiOperation(value = "根据用户查询宿舍楼")
  public Result<List<BuildingQueryRspDTO>> listBuildingForApp(
      @ApiParam(value = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId
  ) {
    return dormitoryBiz.listBuildingForApp(userId);
  }

  @GetMapping("/dormitory/query-by-user")
  @ApiOperation(value = "根据用户查询宿舍楼下的宿舍")
  public Result<List<DormitorySimpleRspDTO>> listDormitoryForApp(
      @ApiParam(value = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(value = "楼栋ID", required = true)
      @RequestParam(name = "buildingId")
      @NotNull(message = "楼栋ID不能为空")
          Long buildingId,
      @ApiParam(value = "楼层", required = true)
      @RequestParam(name = "floorNumber")
      @NotNull(message = "楼层")
          Integer floorNumber

  ) {

    return dormitoryBiz.listDormitoryForApp(userId, buildingId, floorNumber);
  }

  @GetMapping("/dormitory-list-query")
  @ApiOperation(value = "根据条件查询宿舍列表")
  public Result<List<DormitoryClockStatDTO>> listDormitoryClockStatForApp(
      @ApiParam(value = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(value = "楼栋ID", required = true)
      @RequestParam(name = "buildingId")
      @NotNull(message = "楼栋ID不能为空")
          Long buildingId,
      @ApiParam(value = "楼层")
      @RequestParam(name = "floorNumber", required = false)
          Integer floorNumber,
      @ApiParam(value = "宿舍ID")
      @RequestParam(name = "dormitoryId", required = false)
          Long dormitoryId,
      @ApiParam(value = "查寝状态")
      @RequestParam(name = "checkStatus", required = false)
              Boolean checkStatus,
      @ApiParam(value = "排序字段 宿舍号：dormitoryCode 未归人数:stayOutNum 晚归人数:stayOutLateNum")
      @RequestParam(name = "orderBy", required = false)
              String orderBy,
      @ApiParam(value = "升序或降序 desc降序，asc升序")
      @RequestParam(name = "descOrAsc", required = false)
              String descOrAsc

  ) {

    return dormitoryBiz.listDormitoryClockStatForApp(userId, buildingId, floorNumber, dormitoryId, checkStatus,orderBy,descOrAsc);
  }

  @GetMapping("/dormitory/{dormitoryId}/detail/app")
  @ApiOperation(value = "根据用户查询宿舍楼下的宿舍")
  public Result<List<DormitoryStudentStatRspDTO>> getDormitoryClockDetailStatForApp(
      @ApiParam(value = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(value = "宿舍ID", required = true)
      @PathVariable(name = "dormitoryId")
          Long dormitoryId

  ) {

    return dormitoryBiz.getDormitoryClockDetailStatForApp(userId, dormitoryId);
  }

  @GetMapping("/dormitory-check/day-stat")
  @ApiOperation(value = "查寝日统计")
  public Result<DormitoryCheckDayStatRspDTO> dayStat(
      @ApiParam(value = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(value = "年份", required = true)
      @RequestParam(name = "year")
      @Min(value = 1000, message = "年份参数错误")
      @Max(value = 9999, message = "年份参数错误")
      @NotNull(message = "年份不能为空")
          Integer year,
      @ApiParam(value = "月份", required = true)
      @RequestParam(name = "month")
      @Min(value = 1, message = "月份参数错误")
      @Max(value = 12, message = "月份参数错误")
      @NotNull(message = "月份不能为空")
          Integer month,
      @ApiParam(value = "日期", required = true)
      @RequestParam(name = "day")
      @Min(value = 1, message = "日期参数错误")
      @Max(value = 31, message = "日期参数错误")
      @NotNull(message = "日期不能为空")
          Integer day
  ) {
    return dormitoryBiz.dayStat(userId, year, month, day);
  }

  @GetMapping("/dormitory-check/week-stat")
  @ApiOperation(value = "查寝周统计")
  public Result<DormitoryCheckWeekStatRspDTO> weekStat(
      @ApiParam(value = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(value = "周数", required = true)
      @RequestParam(name = "weekNumber")
      @NotNull(message = "周数不能为空")
          Integer weekNumber
  ) {
    return dormitoryBiz.weekStat(userId, weekNumber);
  }

  @GetMapping("/dormitory-check/day-stat/student")
  @ApiOperation(value = "日统计学生列表")
  public Result<List<DormitoryCheckDayStatListRspDTO>> dayStatStudentList(
      @ApiParam(value = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(value = "年份", required = true)
      @RequestParam(name = "year")
      @Min(value = 1000, message = "年份参数错误")
      @Max(value = 9999, message = "年份参数错误")
      @NotNull(message = "年份不能为空")
          Integer year,
      @ApiParam(value = "月份", required = true)
      @RequestParam(name = "month")
      @Min(value = 1, message = "月份参数错误")
      @Max(value = 12, message = "月份参数错误")
      @NotNull(message = "月份不能为空")
          Integer month,
      @ApiParam(value = "日期", required = true)
      @RequestParam(name = "day")
      @Min(value = 1, message = "日期参数错误")
      @Max(value = 31, message = "日期参数错误")
      @NotNull(message = "日期不能为空")
          Integer day,
      @ApiParam(value = "考勤状态", required = true)
      @RequestParam(name = "clockStatus 2到勤，3晚归，4未归")
      @NotNull(message = "考勤状态不能为空 2-4")
      @Min(value = 2, message = "范围2-4")
      @Max(value = 4, message = "范围2-4")
          Byte clockStatus


  ) {
    return dormitoryBiz.dayStatStudentList(userId, year, month, day, clockStatus);
  }

  @GetMapping("/dormitory-check/week-stat/student")
  @ApiOperation(value = "周统计学生列表")
  public Result<List<DormitoryCheckWeekStatListRspDTO>> weekStatStudentList(
      @ApiParam(value = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(value = "周数", required = true)
      @RequestParam(name = "weekNumber")
      @NotNull(message = "周数不能为空")
          Integer weekNumber,
      @ApiParam(value = "考勤状态", required = true)
      @RequestParam(name = "clockStatus")
      @NotNull(message = "考勤状态不能为空 2-4")
      @Min(value = 2, message = "范围2-4")
      @Max(value = 4, message = "范围2-4")
          Byte clockStatus


  ) {
    return dormitoryBiz.weekStatStudentList(userId, weekNumber, clockStatus);
  }


  @GetMapping("/dormitory-check/query-by-name-code")
  @ApiOperation(value = "根据姓名和学号查询学生列表")
  public Result<List<StudentDormitoryRsqDTO>> queryStudent(
      @ApiParam(value = "用户ID", required = true)
      @RequestParam(name = "userId")
      @NotNull(message = "用户ID不能为空")
          Long userId,
      @ApiParam(value = "姓名或学/工号")
      @RequestParam(name = "nameOrCode", required = true)
      @NotBlank(message = "搜素条件不能为空")
          String nameOrCode
  ) {

    return dormitoryBiz.queryStudent(userId, nameOrCode);
  }


  @PostMapping("/dormitory-check")
  @ApiOperation(value = "结束查寝")
  public Result addDormitoryCheck(@Valid @RequestBody DormitoryCheckOverReqDTO reqDTO) {
    return dormitoryBiz.addDormitoryCheck(reqDTO);
  }
}
