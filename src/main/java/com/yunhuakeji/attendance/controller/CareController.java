package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.CareUpdateReqDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "关怀操作接口")
@Controller
public class CareController {

  @GetMapping("/care-instructor")
  @ApiOperation(value = "分页查询辅导员关怀或待关怀列表")
  public PagedResult<CareTaskBaseInfoDTO> listAll(
      @ApiParam(name = "辅导员ID", required = true)
      @RequestParam(name = "instructorId")
      @NotNull(message = "辅导员ID不能为空")
          Long instructorId,
      @ApiParam(name = "关怀状态，1待关怀 2已关怀", required = true)
      @RequestParam(name = "careStatus")
      @NotNull(message = "关怀状态不能为空")
          Integer careStatus
  ) {
    return null;
  }

  @PutMapping("/care")
  @ApiOperation(value = "提交关怀结果")
  public Result updateCare(CareUpdateReqDTO reqDTO) {
    return null;
  }

  @GetMapping("/care-student")
  @ApiOperation(value = "分页查询学生已关怀列表")
  public PagedResult<CareTaskBaseInfoDTO> listCaredByStudent(
      @ApiParam(name = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId) {
    return null;
  }


}
