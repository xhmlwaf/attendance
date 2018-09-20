package com.yunhuakeji.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.yunhuakeji.attendance.biz.CareBiz;
import com.yunhuakeji.attendance.biz.InstructorClockBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.CareUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockDetailRspDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockStatRsqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorStatRspDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Validated
@Api(value = "辅导员操作接口")
@RestController
public class InstructorOperateController {

  private static final Logger logger = LoggerFactory.getLogger(InstructorOperateController.class);

  @Autowired
  private CareBiz careBiz;

  @Autowired
  private InstructorClockBiz instructorClockBiz;

  @GetMapping("/instructor-clock/stat-all-count")
  @ApiOperation(value = "根据辅导员ID统计总打卡次数")
  public Result<InstructorClockStatRsqDTO> statAllCount(
      @ApiParam(value = "辅导员用户ID", required = true)
      @RequestParam(name = "instructorId")
      @NotNull(message = "辅导员ID不能为空")
          Long instructorId
  ) {
    return instructorClockBiz.statAllCount(instructorId);
  }

  @GetMapping("/instructor-clock/stat-by-year-month")
  @ApiOperation(value = "根据年月辅导员考勤统计")
  public Result<List<String>> statByYearAndMonth(
      @ApiParam(value = "辅导员用户ID", required = true)
      @RequestParam(name = "instructorId")
      @NotNull(message = "辅导员用户ID不能为空")
          Long instructorId,
      @ApiParam(value = "年份", required = true)
      @RequestParam(name = "year")
      @NotNull(message = "年份不能为空")
      @Min(value = 1000, message = "不合法的年份")
      @Max(value = 9999, message = "不合法的年份")
          Integer year,
      @ApiParam(value = "月份", required = true)
      @RequestParam(name = "month")
      @NotNull(message = "月份不能为空")
      @Min(value = 1, message = "不合法的月份")
      @Max(value = 12, message = "不合法的月份")
          Integer month
  ) {
    return instructorClockBiz.statByYearAndMonth(instructorId, year, month);
  }


  @GetMapping("/instructor-clock")
  @ApiOperation(value = "辅导员打卡记录分页查询")
  public PagedResult<InstructorClockDetailRspDTO> statAllClock(
      @ApiParam(value = "辅导员用户ID", required = true)
      @RequestParam(name = "instructorId")
      @NotNull(message = "辅导员用户ID不能为空")
          Long instructorId,
      @ApiParam(value = "页码，从1开始，默认1", required = true)
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @ApiParam(value = "页大小，默认10", required = true)
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {

    return instructorClockBiz.statAllClock(instructorId, pageNo, pageSize);
  }


  @PostMapping("/instructor-clock")
  @ApiOperation(value = "辅导员打卡")
  public Result instructorClock(@Valid @RequestBody InstructorClockReqDTO req) {
    logger.info("params:" + JSON.toJSONString(req));
    return instructorClockBiz.instructorClock(req);
  }

  @GetMapping("/care-instructor")
  @ApiOperation(value = "分页查询辅导员关怀或待关怀列表")
  public PagedResult<CareTaskBaseInfoDTO> listByInstructor(
      @ApiParam(value = "辅导员ID", required = true)
      @RequestParam(name = "instructorId")
      @NotNull(message = "辅导员ID不能为空")
          Long instructorId,
      @ApiParam(value = "关怀状态，1待关怀 2已关怀", required = true)
      @RequestParam(name = "careStatus")
      @NotNull(message = "关怀状态不能为空")
          Byte careStatus,
      @ApiParam(value = "页码，从1开始，默认1")
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @ApiParam(value = "页大小，默认10")
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return careBiz.listByInstructor(instructorId, careStatus, pageNo, pageSize);
  }

  @PutMapping("/care")
  @ApiOperation(value = "提交关怀结果")
  public Result updateCare(@RequestBody @Valid CareUpdateReqDTO reqDTO) {

    logger.info("params:" + JSON.toJSONString(reqDTO));

    return careBiz.updateCare(reqDTO);
  }


  @GetMapping("/analysis/instructor-stat")
  @ApiOperation(value = "辅导员查寝签到-分页获取辅导员打卡统计")
  public PagedResult<InstructorStatRspDTO> instructorStatPage(
      @ApiParam(value = "姓名或学/工号（姓名或学/工号不为空时将忽略其他查询条件）")
      @RequestParam(name = "nameOrCode", required = false)
          String nameOrCode,
      @ApiParam(value = "机构ID")
      @RequestParam(name = "orgId", required = false)
          Long orgId,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize,
      @ApiParam(value = "排序字段 负责学生数：responsibleStudent 打卡次数：clockCount 处理关怀数:dealCareCount 累计未归学生:totalLayOutCount 累计晚归学生:totalLayOutLateCount")
      @RequestParam(name = "orderBy", required = false)
          String orderBy,
      @ApiParam(value = "升序或降序 desc降序，asc升序")
      @RequestParam(name = "descOrAsc", required = false)
          String descOrAsc,
      @ApiParam(value = "用户ID")
      @RequestParam(name = "userId",required = false)
              Long userId
  ) {
    return instructorClockBiz.instructorStatPage(nameOrCode, orgId, pageNo, pageSize, orderBy, descOrAsc,userId);

  }

  @GetMapping("/instructor-clock-status")
  @ApiOperation(value = "根据辅导员ID查询当前考勤状态 1未打卡，2已打卡 ")
  public Result<Byte> getInstructorClockStatusByDay(
      @ApiParam(value = "辅导员ID", required = true)
      @RequestParam(name = "instructorId")
      @NotNull(message = "辅导员ID不能为空")
          Long instructorId
  ) {

    return instructorClockBiz.getInstructorClockStatusByDay(instructorId);
  }

}
