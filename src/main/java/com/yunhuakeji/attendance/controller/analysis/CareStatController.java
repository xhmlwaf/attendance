package com.yunhuakeji.attendance.controller.analysis;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.dto.response.CanStartCareRspDTO;
import com.yunhuakeji.attendance.dto.response.InstructorStatRspDTO;
import com.yunhuakeji.attendance.dto.response.StudentCareRspDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "学生关怀模块接口")
@Controller
public class CareStatController {

  @GetMapping("/analysis/care/can-start")
  @ApiOperation(value = "分页获取可发起关怀列表")
  public PagedResult<CanStartCareRspDTO> canStartCarePage(
      @ApiParam(name = "姓名")
      @RequestParam(name = "name", required = false)
          String name,
      @ApiParam(name = "学号")
      @RequestParam(name = "studentCode", required = false)
          String studentCode,
      @ApiParam(name = "机构ID")
      @RequestParam(name = "orgId", required = false)
          Long orgId,
      @ApiParam(name = "专业ID")
      @RequestParam(name = "majorId", required = false)
          Long majorId,
      @ApiParam(name = "辅导员ID")
      @RequestParam(name = "instructor", required = false)
          Long instructor,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return null;

  }


  @GetMapping("/analysis/student-care")
  @ApiOperation(value = "分页获取已发起/已处理关怀列表")
  public PagedResult<StudentCareRspDTO> studentCarePage(
      @ApiParam(name = "关怀状态")
      @RequestParam(name = "careStatus", required = false)
      @NotNull(message = "关怀状态不能为空")
          Byte careStatus,
      @ApiParam(name = "姓名")
      @RequestParam(name = "name", required = false)
          String name,
      @ApiParam(name = "学号")
      @RequestParam(name = "studentCode", required = false)
          String studentCode,
      @ApiParam(name = "机构ID")
      @RequestParam(name = "orgId", required = false)
          Long orgId,
      @ApiParam(name = "专业ID")
      @RequestParam(name = "majorId", required = false)
          Long majorId,
      @ApiParam(name = "辅导员ID")
      @RequestParam(name = "instructor", required = false)
          Long instructor,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return null;

  }


  @GetMapping("/analysis/instructor-stat")
  @ApiOperation(value = "分页获取辅导员打卡统计")
  public PagedResult<InstructorStatRspDTO> instructorStatPage(
      @ApiParam(name = "姓名")
      @RequestParam(name = "name", required = false)
          String name,
      @ApiParam(name = "学号")
      @RequestParam(name = "studentCode", required = false)
          String studentCode,
      @ApiParam(name = "机构ID")
      @RequestParam(name = "orgId", required = false)
          Long orgId,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return null;

  }


}
