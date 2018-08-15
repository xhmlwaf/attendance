package com.yunhuakeji.attendance.controller.analysis;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import java.util.Date;

@Api(value = "晚归考勤分析模块接口")
@RestController
public class AnalysisController {


  @GetMapping("/analysis/exeception-stat-by-day")
  @ApiOperation(value = "每日异常数据统计")
  public Result<AnalysisExceptionStatByDayRsqDTO> getAnalysisExceptionStatByDay(
      @ApiParam(value = "机构ID,不填表示全部")
      @RequestParam(name = "orgId")
          Long orgId,
      @ApiParam(value = "日期 yyyy-MM-dd", required = true)
      @RequestParam(name = "date")
      @NotNull(message = "日期不能为空")
          Date date
  ) {

    return null;
  }

  @GetMapping("/analysis/exeception-clock-by-day")
  @ApiOperation(value = "每日异常数据分页查询")
  public PagedResult<AnalysisExceptionClockByDayRsqDTO> getAnalysisExceptionClockByDay(
      @ApiParam(value = "姓名或学/工号（姓名或学/工号不为空时将忽略其他查询条件）")
      @RequestParam(name = "nameOrCode", required = false)
          String nameOrCode,
      @ApiParam(value = "机构ID")
      @RequestParam(name = "orgId")
          Long orgId,
      @ApiParam(value = "专业ID")
      @RequestParam(name = "majorId", required = false)
          Long majorId,
      @ApiParam(value = "辅导员ID")
      @RequestParam(name = "instructor", required = false)
          Long instructor,
      @ApiParam(value = "状态 2到勤，3晚归，4未归")
      @RequestParam(name = "clockStatus", required = false)
          Byte clockStatus,
      @ApiParam(value = "日期（格式yyyy-MM-dd）", required = true)
      @RequestParam(name = "date")
      @NotNull(message = "日期不能为空")
      @DateTimeFormat(pattern = "yyyy-MM-dd")
          Date date,
      @ApiParam(value = "排序字段 连续未归：continuousStayoutDays 连续晚归：continuousStayoutLateDays")
      @RequestParam(name = "orderBy", required = false)
          String orderBy,
      @ApiParam(value = "升序或降序 desc降序，asc升序")
      @RequestParam(name = "descOrAsc", required = false)
          String descOrAsc

  ) {
    return null;
  }

  @GetMapping("/analysis/exeception-stat-by-week")
  @ApiOperation(value = "每周异常数据统计")
  public Result<AnalysisExceptionStatByWeekRsqDTO> getAnalysisExceptionStatByWeek(
      @ApiParam(value = "机构ID,不填表示全部")
      @RequestParam(name = "orgId")
          Long orgId,
      @ApiParam(value = "周数", required = true)
      @RequestParam(name = "weekNumber")
      @NotNull(message = "周数")
          int weekNumber
  ) {

    return null;
  }

  @GetMapping("/analysis/exeception-stat-by-day-of-week")
  @ApiOperation(value = "每周异常数据列表统计")
  public Result<AnalysisExceptionStatByDayOfWeekRsqDTO> getAnalysisExceptionStatListByWeek(
      @ApiParam(value = "机构ID")
      @RequestParam(name = "orgId")
          Long orgId,
      @ApiParam(value = "周数", required = true)
      @RequestParam(name = "weekNum")
      @NotNull(message = "周数")
          int weekNum
  ) {

    return null;
  }

  @GetMapping("/analysis/exeception-clock-by-week")
  @ApiOperation(value = "每周异常数据分页查询")
  public PagedResult<AnalysisExceptionClockByWeekRsqDTO> getAnalysisExceptionClockByWeek(
      @ApiParam(value = "姓名或学/工号（姓名或学/工号不为空时将忽略其他查询条件）")
      @RequestParam(name = "nameOrCode", required = false)
          String nameOrCode,
      @ApiParam(value = "机构ID")
      @RequestParam(name = "orgId")
          Long orgId,
      @ApiParam(value = "专业ID")
      @RequestParam(name = "majorId", required = false)
          Long majorId,
      @ApiParam(value = "辅导员ID")
      @RequestParam(name = "instructor", required = false)
          Long instructor,
      @ApiParam(value = "状态")
      @RequestParam(name = "clockStatus", required = false)
          Byte clockStatus,
      @ApiParam(value = "周数", required = true)
      @RequestParam(name = "weekNum")
      @NotNull(message = "周数")
          int weekNum,
      @ApiParam(value = "排序字段 周未归次数：stayoutDays 周晚归次数：stayoutLateDays")
      @RequestParam(name = "orderBy", required = false)
          String orderBy,
      @ApiParam(value = "升序或降序 desc降序，asc升序")
      @RequestParam(name = "descOrAsc", required = false)
          String descOrAsc
  ) {
    return null;
  }


}
