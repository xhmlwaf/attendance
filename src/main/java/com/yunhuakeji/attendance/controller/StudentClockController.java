package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.StudentClockAddReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentClockUpdateReqDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockQueryRsqDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockStatRspDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api(value = "学生考勤操作接口")
@Controller
public class StudentClockController {

  @PostMapping("/student-clock")
  @ApiOperation(value = "学生打卡")
  public Result<Boolean> clock(@Valid @RequestBody StudentClockAddReqDTO req) {

    return null;
  }

  @GetMapping("/student-clock/{id}/stat")
  @ApiOperation(value = "统计学生累计晚归，到勤，未归次数")
  public Result<StudentClockStatRspDTO> statClock(
      @ApiParam(name = "学生ID", required = true)
      @PathVariable(name = "id")
      @NotBlank(message = "学生ID不能为空")
          String id) {
    return null;
  }

  @GetMapping("/student-clock")
  @ApiOperation(value = "查询打卡历史")
  public PagedResult<StudentClockQueryRsqDTO> listAll(
      @ApiParam(name = "学生ID", required = true)
      @RequestParam(name = "id")
      @NotBlank(message = "学生ID不能为空")
          String id,
      @ApiParam(name = "年份", required = true)
      @RequestParam(name = "year")
      @NotNull(message = "年份不能为空")
          Integer year,
      @ApiParam(name = "月份", required = true)
      @RequestParam(name = "month")
      @NotNull(message = "月份不能为空")
          Integer month
  ) {
    return null;
  }



  @PutMapping("/student-clock")
  @ApiOperation(value = "更新学生打卡记录")
  public Result update(@Valid @RequestBody StudentClockUpdateReqDTO reqDTO) {
    return null;
  }

}
