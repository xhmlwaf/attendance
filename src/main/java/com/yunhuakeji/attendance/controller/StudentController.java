package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "学生操作接口")
@Controller
public class StudentController {

  @GetMapping("/student/{id}")
  @ApiOperation(value = "根据学生ID查询学生基本信息")
  public Result<StudentBaseInfoDTO> getStudentBaseInfo(
      @ApiParam(name = "学生ID", required = true)
      @PathVariable(name = "id")
      @NotNull(message = "学生ID不能为空")
      @Min(value = 1, message = "ID不合法")
          Long id
  ) {

    return null;
  }
}
