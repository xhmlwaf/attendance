package com.yunhuakeji.attendance.controller;

import com.alibaba.fastjson.JSON;
import com.yunhuakeji.attendance.biz.CareBiz;
import com.yunhuakeji.attendance.biz.StudentClockBiz;
import com.yunhuakeji.attendance.biz.StudentClockHistoryBiz;
import com.yunhuakeji.attendance.biz.UserRoleManageBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.StudentClockAddReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentClockBatchUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentClockUpdateReqDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockHistoryQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockQueryRsqDTO;
import com.yunhuakeji.attendance.dto.response.StudentClockStatRspDTO;
import com.yunhuakeji.attendance.dto.response.TimeClockStatusDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Validated
@Api(value = "学生操作接口")
@RestController
public class StudentOperateController {

  private static final Logger logger = LoggerFactory.getLogger(StudentOperateController.class);

  @Autowired
  private StudentClockBiz studentClockBiz;

  @Autowired
  private StudentClockHistoryBiz studentClockHistoryBiz;

  @Autowired
  private CareBiz careBiz;

  @Autowired
  private UserRoleManageBiz userRoleManageBiz;

  @GetMapping("/student-clock-history")
  @ApiOperation(value = "根据学生ID和日期查询全部历史")
  public Result<List<StudentClockHistoryQueryRspDTO>> listAll(
      @ApiParam(value = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId,
      @ApiParam(value = "日期（格式yyyy-MM-dd）", required = true)
      @RequestParam(name = "date")
      @NotNull(message = "日期不能为空")
      @DateTimeFormat(pattern = "yyyy-MM-dd")
          Date date
  ) {

    return studentClockHistoryBiz.listAll(studentId, date);
  }

  @GetMapping("/student-clock-status")
  @ApiOperation(value = "根据学生ID查询当前考勤状态 1未打卡，2到勤，3晚归，4未归 ")
  public Result<Byte> getStudentClockStatusByDay(
      @ApiParam(value = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId
  ) {

    return studentClockBiz.getStudentClockStatusByDay(studentId);
  }

  @PostMapping("/student-clock")
  @ApiOperation(value = "学生打卡")
  public Result clock(@Valid @RequestBody StudentClockAddReqDTO req) {
    logger.info("params:" + JSON.toJSONString(req));
    return studentClockBiz.clock(req);
  }

  @GetMapping("/check-device")
  @ApiOperation(value = "判断常用设备")
  public Result<Boolean> checkDevice(
      @ApiParam(value = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId,
      @ApiParam(value = "设备ID", required = true)
      @RequestParam(name = "deviceId")
      @NotNull(message = "设备ID不能为空")
          String deviceId

  ) {
    return studentClockBiz.checkDevice(studentId, deviceId);
  }

  @GetMapping("/check-position")
  @ApiOperation(value = "判断坐标是否在打卡范围内")
  public Result<Boolean> checkPosition(
      @ApiParam(value = "经度", required = true)
      @NotNull(message = "经度不能为空")
      @RequestParam(name = "posLongitude")
          BigDecimal posLongitude,
      @ApiParam(value = "纬度", required = true)
      @NotNull(message = "纬度不能为空")
      @RequestParam(name = "posLatitude")
          BigDecimal posLatitude
  ) {
    return studentClockBiz.checkPosition(posLongitude, posLatitude);
  }

  @GetMapping("/student-clock/{studentId}/stat")
  @ApiOperation(value = "统计学生累计晚归，到勤，未归次数(个人详情页需要调用)")
  public Result<StudentClockStatRspDTO> statClockByStudent(
      @ApiParam(value = "学生ID", required = true)
      @PathVariable(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId) {
    return studentClockBiz.statClockByStudent(studentId);
  }

  @GetMapping("/student-clock")
  @ApiOperation(value = "根据年月查询打卡记录")
  public Result<List<StudentClockQueryRsqDTO>> listByYearMonth(
      @ApiParam(value = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId,
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
    return studentClockBiz.listByYearMonth(studentId, year, month);
  }

  @GetMapping("/student-clock/list-by-week")
  @ApiOperation(value = "根据周数打卡记录")
  public Result<List<TimeClockStatusDTO>> listByWeekNumber(
      @ApiParam(value = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId,
      @ApiParam(value = "周数", required = true)
      @RequestParam(name = "weekNumber")
      @NotNull(message = "周数不能为空")
          int weekNumber
  ) {

    return studentClockBiz.listByWeekNumber(studentId, weekNumber);

  }

  @GetMapping("/student/{studentId}")
  @ApiOperation(value = "根据学生ID查询学生基本信息")
  public Result<StudentBaseInfoDTO> getStudentBaseInfo(
      @ApiParam(value = "学生ID", required = true)
      @PathVariable(name = "studentId")
      @NotNull(message = "学生ID不能为空")
      @Min(value = 1, message = "ID不合法")
          Long studentId
  ) {

    return userRoleManageBiz.getStudentBaseInfo(studentId);
  }

  @GetMapping("/care-student")
  @ApiOperation(value = "个人详情页-分页查询学生已关怀列表(个人详情页需要调用)")
  public PagedResult<CareTaskBaseInfoDTO> listCaredByStudent(
      @ApiParam(value = "学生ID", required = true)
      @RequestParam(name = "studentId")
      @NotNull(message = "学生ID不能为空")
          Long studentId,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize) {
    return careBiz.listByStudent(studentId, pageNo, pageSize);
  }


  @PutMapping("/student-clock")
  @ApiOperation(value = "更新学生打卡记录")
  public Result update(@Valid @RequestBody StudentClockUpdateReqDTO reqDTO) {
    logger.info("params:" + JSON.toJSONString(reqDTO));
    return studentClockBiz.update(reqDTO);
  }

  @PutMapping("/student-clock/batch")
  @ApiOperation(value = "更新学生打卡记录")
  public Result updateBatch(@Valid @RequestBody StudentClockBatchUpdateReqDTO reqDTO) {
    logger.info("params:" + JSON.toJSONString(reqDTO));
    return studentClockBiz.updateBatch(reqDTO);
  }



}
