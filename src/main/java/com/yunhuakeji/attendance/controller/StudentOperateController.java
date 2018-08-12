package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.biz.StudentClockBiz;
import com.yunhuakeji.attendance.biz.StudentClockHistoryBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.StudentClockAddReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentClockUpdateReqDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockHistoryQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockQueryRsqDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockStatRspDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "学生操作接口")
@Controller
public class StudentOperateController {

  @Autowired
  private StudentClockBiz studentClockBiz;

  @Autowired
  private StudentClockHistoryBiz studentClockHistoryBiz;

  @GetMapping("/student-clock-history")
  @ApiOperation(value = "根据学生ID和日期查询全部历史")
  public Result<List<StudentClockHistoryQueryRspDTO>> listAll(
      @ApiParam(name = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId,
      @ApiParam(name = "date", value = "日期（格式yyyy-MM-dd）", required = true)
      @RequestParam(name = "date")
      @NotNull(message = "日期不能为空")
      @DateTimeFormat(pattern = "yyyy-MM-dd")
          Date date
  ) {

    return studentClockHistoryBiz.listAll(studentId, date);
  }


  @PostMapping("/student-clock")
  @ApiOperation(value = "学生打卡")
  public Result clock(@Valid @RequestBody StudentClockAddReqDTO req) {
    return studentClockBiz.clock(req);
  }

  @GetMapping("/student-clock/{studentId}/stat")
  @ApiOperation(value = "统计学生累计晚归，到勤，未归次数(个人详情页需要调用)")
  public Result<StudentClockStatRspDTO> statClockByStudent(
      @ApiParam(name = "学生ID", required = true)
      @PathVariable(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId) {
    return studentClockBiz.statClockByStudent(studentId);
  }

  @GetMapping("/student-clock")
  @ApiOperation(value = "查询打卡记录")
  public Result<List<StudentClockQueryRsqDTO>> listByYearMonth(
      @ApiParam(name = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId,
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
    return studentClockBiz.listByYearMonth(studentId, year, month);
  }

  @GetMapping("/student/{id}")
  @ApiOperation(value = "根据学生ID查询学生基本信息")
  public Result<StudentBaseInfoDTO> getStudentBaseInfo(
      @ApiParam(name = "学生ID", required = true)
      @PathVariable(name = "studentId")
      @NotNull(message = "学生ID不能为空")
      @Min(value = 1, message = "ID不合法")
          Long studentId
  ) {

    return null;
  }

  @GetMapping("/care-student")
  @ApiOperation(value = "个人详情页-分页查询学生已关怀列表(个人详情页需要调用)")
  public PagedResult<CareTaskBaseInfoDTO> listCaredByStudent(
      @ApiParam(name = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId) {
    return null;
  }


  @PutMapping("/student-clock")
  @ApiOperation(value = "更新学生打卡记录")
  public Result update(@Valid @RequestBody StudentClockUpdateReqDTO reqDTO) {
    return null;
  }


}
