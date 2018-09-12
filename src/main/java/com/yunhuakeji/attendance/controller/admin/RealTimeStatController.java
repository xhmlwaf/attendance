package com.yunhuakeji.attendance.controller.admin;

import com.yunhuakeji.attendance.biz.RealTimeStatBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.ClockStatByBuildingRspDTO;
import com.yunhuakeji.attendance.dto.response.ClockStatByStudentRspDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.List;

@Validated
@Api(value = "实时统计模块接口")
@RestController
public class RealTimeStatController {

  @Autowired
  private RealTimeStatBiz realTimeStatBiz;

  @GetMapping("/real-time-stat/clock-stat-by-bulding")
  @ApiOperation(value = "按宿舍实时统计 只区分打卡未打卡")
  public Result<List<ClockStatByBuildingRspDTO>> realTimeStatByBuilding() {
    return realTimeStatBiz.realTimeStatByBuilding();
  }

  @GetMapping("/real-time-stat/clock-stat-by-student")
  @ApiOperation(value = "学生实时统计 打卡未打卡")
  public PagedResult<ClockStatByStudentRspDTO> realTimeStatByStudent(
      @ApiParam(value = "楼栋ID", required = true)
      @RequestParam(name = "buildingId")
          Long buildingId,
      @ApiParam(value = "页码")
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @ApiParam(value = "页大小")
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return realTimeStatBiz.realTimeStatByStudent(buildingId, pageNo, pageSize);
  }

}
