package com.yunhuakeji.attendance.controller.analysis;

import com.yunhuakeji.attendance.biz.CareBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.DeleteCareReqDTO;
import com.yunhuakeji.attendance.dto.request.StartCareReqDTO;
import com.yunhuakeji.attendance.dto.response.CanStartCareRspDTO;
import com.yunhuakeji.attendance.dto.response.StudentCareRspDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.web.bind.annotation.RestController;

@Validated
@Api(value = "学生关怀模块接口")
@RestController
public class CareStatController {

  @Autowired
  private CareBiz careBiz;

  @GetMapping("/analysis/care/can-start")
  @ApiOperation(value = "分页获取可发起关怀列表")
  public PagedResult<CanStartCareRspDTO> canStartCarePage(
      @ApiParam(value = "姓名或学/工号（姓名或学/工号不为空时将忽略其他查询条件）")
      @RequestParam(name = "nameOrCode", required = false)
          String nameOrCode,
      @ApiParam(value = "机构ID")
      @RequestParam(name = "orgId", required = false)
          Long orgId,
      @ApiParam(value = "专业ID")
      @RequestParam(name = "majorId", required = false)
          Long majorId,
      @ApiParam(value = "辅导员ID")
      @RequestParam(name = "instructorId", required = false)
          Long instructorId,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize,
      @ApiParam(value = "排序字段 连续未归：continuousStayoutDays 连续晚归：continuousStayoutLateDays 累计被关怀次数:totalCared")
      @RequestParam(name = "orderBy", required = false)
          String orderBy,
      @ApiParam(value = "升序或降序 desc降序，asc升序")
      @RequestParam(name = "descOrAsc", required = false)
          String descOrAsc
  ) {
    return careBiz.canStartCarePage(nameOrCode, orgId, majorId, instructorId, pageNo, pageSize, orderBy, descOrAsc);

  }


  @GetMapping("/analysis/student-care")
  @ApiOperation(value = "分页获取已发起/已处理关怀列表")
  public PagedResult<StudentCareRspDTO> studentCarePage(
      @ApiParam(value = "关怀状态1：未关怀 2：已关怀",required = true)
      @RequestParam(name = "careStatus")
      @NotNull(message = "关怀状态不能为空")
      @Min(value = 1, message = "范围1-2")
      @Max(value = 2, message = "范围1-2")
          Byte careStatus,
      @ApiParam(value = "姓名或学/工号（姓名或学/工号不为空时将忽略其他查询条件）")
      @RequestParam(name = "nameOrCode", required = false)
          String nameOrCode,
      @ApiParam(value = "机构ID")
      @RequestParam(name = "orgId", required = false)
          Long orgId,
      @ApiParam(value = "专业ID")
      @RequestParam(name = "majorId", required = false)
          Long majorId,
      @ApiParam(value = "辅导员ID")
      @RequestParam(name = "instructorId", required = false)
          Long instructorId,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return careBiz.studentCarePage(careStatus, nameOrCode, orgId, majorId, instructorId, pageNo, pageSize);

  }

  @PostMapping("/analysis/start-student-care")
  @ApiOperation(value = "发起学生关怀")
  public Result startCare(StartCareReqDTO startCareReqDTO) {

    return careBiz.startCare(startCareReqDTO);
  }

  @PutMapping("/analysis/delete-student-care")
  @ApiOperation(value = "撤销学生关怀")
  public Result deleteCare(DeleteCareReqDTO deleteCareReqDTO) {

    return careBiz.deleteCare(deleteCareReqDTO);
  }


}
