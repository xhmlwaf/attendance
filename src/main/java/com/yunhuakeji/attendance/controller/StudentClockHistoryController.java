package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.biz.StudentClockHistoryBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.StudentClockHistoryQueryRspDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "学生考勤历史操作接口")
@Controller
public class StudentClockHistoryController {

  @Autowired
  private StudentClockHistoryBiz studentClockHistoryBiz;

  @GetMapping("/student-clock-history")
  @ApiOperation(value = "根据学生ID和日期查询全部历史")
  public Result<List<StudentClockHistoryQueryRspDTO>> listAll(
      @ApiParam(name = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId,
      @ApiParam(name = "date", value = "开始时间（格式yyyy-MM-dd）", required = true)
      @RequestParam(name = "date")
      @NotNull(message = "日期不能为空")
      @DateTimeFormat(pattern = "yyyy-MM-dd")
          Date date
  ) {

    return studentClockHistoryBiz.listAll(studentId,date);
  }
}
