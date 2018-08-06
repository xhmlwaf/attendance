package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.biz.CareBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.CareUpdateReqDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "关怀操作接口")
@Controller
public class CareController {

  @Autowired
  private CareBiz careBiz;

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
