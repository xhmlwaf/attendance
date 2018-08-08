package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.biz.DormitoryBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckDayStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckWeekStatRspDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "查寝操作接口")
@Controller
public class DormitoryCheckController {

  @Autowired
  private DormitoryBiz dormitoryBiz;

  @GetMapping("/dormitory-check/day-stat")
  @ApiOperation(value = "查寝日统计")
  public Result<DormitoryCheckDayStatRspDTO> dayStat(
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
      @ApiParam(name = "年份", required = true)
      @RequestParam(name = "year")
      @Min(value = 1000, message = "年份参数错误")
      @Max(value = 9999, message = "年份参数错误")
      @NotNull(message = "年份不能为空")
          Integer year,
      @ApiParam(name = "月份", required = true)
      @RequestParam(name = "month")
      @Min(value = 1, message = "月份参数错误")
      @Max(value = 12, message = "月份参数错误")
      @NotNull(message = "月份不能为空")
          Integer month,
      @ApiParam(name = "日期", required = true)
      @RequestParam(name = "day")
      @Min(value = 1, message = "日期参数错误")
      @Max(value = 31, message = "日期参数错误")
      @NotNull(message = "日期不能为空")
          Integer day
  ) {
    return null;
  }

    @GetMapping("/dormitory-check/week-stat")
    @ApiOperation(value = "查寝日统计")
    public Result<DormitoryCheckWeekStatRspDTO> weekStat(
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
            @ApiParam(name = "周数", required = true)
            @RequestParam(name = "weekNumber")
            @NotNull(message = "周数不能为空")
                    Integer weekNumber
    ) {
        return null;
    }
}
