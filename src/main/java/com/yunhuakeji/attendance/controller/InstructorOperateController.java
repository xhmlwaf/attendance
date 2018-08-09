package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.biz.CareBiz;
import com.yunhuakeji.attendance.biz.InstructorClockBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.CareUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockStatRsqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorQueryRspDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "辅导员操作接口")
@Controller
public class InstructorOperateController {

  @Autowired
  private CareBiz careBiz;

  @Autowired
  private InstructorClockBiz instructorClockBiz;

  @GetMapping("/instructor-clock/stat-all-count")
  @ApiOperation(value = "辅导员总打卡次数")
  public Result<InstructorClockStatRsqDTO> statAllCount(
      @ApiParam(name = "辅导员用户ID", required = true)
      @RequestParam(name = "instructorId")
      @NotNull(message = "辅导员ID不能为空")
          Long instructorId
  ) {
    return instructorClockBiz.statAllCount(instructorId);
  }

  @GetMapping("/instructor-clock/stat-by-year-month")
  @ApiOperation(value = "辅导员考勤统计")
  public Result<List<String>> statByYearAndMonth(
      @ApiParam(name = "辅导员用户ID", required = true)
      @RequestParam(name = "instructorId")
      @NotNull(message = "辅导员用户ID不能为空")
          Long instructorId,
      @ApiParam(name = "年份", required = true)
      @RequestParam(name = "year")
      @NotNull(message = "年份不能为空")
      @Min(value = 1000, message = "不合法的年份")
      @Max(value = 9999, message = "不合法的年份")
          Integer year,
      @ApiParam(name = "月份", required = true)
      @RequestParam(name = "month")
      @NotNull(message = "月份不能为空")
      @Min(value = 1, message = "不合法的月份")
      @Max(value = 12, message = "不合法的月份")
          Integer month
  ) {
    return null;
  }

  @GetMapping("/instructor-clock/export-excel")
  @ApiOperation(value = "辅导员考勤统计导出excel")
  public void statExportExcel(
      @ApiParam(name = "辅导员用户ID", required = true)
      @RequestParam(name = "instructorId")
      @NotNull(message = "辅导员用户ID不能为空")
          Long instructorId
  ) {

  }

  @PostMapping("/instructor-clock")
  @ApiOperation(value = "辅导员打卡")
  public Result instructorClock(@Valid @RequestBody InstructorClockReqDTO req) {
    return instructorClockBiz.instructorClock(req);
  }

  @GetMapping("/care-instructor")
  @ApiOperation(value = "分页查询辅导员关怀或待关怀列表")
  public PagedResult<CareTaskBaseInfoDTO> listByInstructor(
      @ApiParam(name = "辅导员ID", required = true)
      @RequestParam(name = "instructorId")
      @NotNull(message = "辅导员ID不能为空")
          Long instructorId,
      @ApiParam(name = "关怀状态，1待关怀 2已关怀", required = true)
      @RequestParam(name = "careStatus")
      @Size(min = 1, max = 2, message = "关怀状态值 1待关怀 2已关怀")
      @NotNull(message = "关怀状态不能为空")
          Byte careStatus,
      @ApiParam(name = "页码，从1开始，默认1", required = true)
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @ApiParam(name = "页大小，默认10", required = true)
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return careBiz.listByInstructor(instructorId, careStatus, pageNo, pageSize);
  }

  @PutMapping("/care")
  @ApiOperation(value = "提交关怀结果")
  public Result updateCare(CareUpdateReqDTO reqDTO) {
    return careBiz.updateCare(reqDTO);
  }

}
