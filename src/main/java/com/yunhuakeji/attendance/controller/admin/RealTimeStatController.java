package com.yunhuakeji.attendance.controller.admin;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.ClockStatByBuildingRspDTO;
import com.yunhuakeji.attendance.dto.response.ClockStatByStudentRspDTO;

import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.constraints.Min;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "实时统计模块接口")
@Controller
public class RealTimeStatController {

  @GetMapping("/real-time-stat/clock-stat-by-bulding")
  @ApiOperation(value = "实时统计")
  public Result<List<ClockStatByBuildingRspDTO>> realTimeStatByBuilding() {

    return null;
  }

  @GetMapping("/real-time-stat/clock-stat-by-student")
  @ApiOperation(value = "实时统计")
  public PagedResult<ClockStatByStudentRspDTO> realTimeStatByStudent(
          @ApiParam(value = "页码")
          @RequestParam(value = "pageNo", required = false, defaultValue = "1")
          @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
          @ApiParam(value = "页大小")
          @RequestParam(value = "pageSize", required = false, defaultValue = "10")
          @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return null;
  }

}
