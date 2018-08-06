package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.biz.InstructorClockBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockStatRsqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Api(value = "辅导员考勤操作接口")
@Controller
public class InstructorClockController {

    @Autowired
    private InstructorClockBiz instructorClockBiz;

    @GetMapping("/instructor-clock/stat-all-count")
    @ApiOperation(value = "辅导员总打卡次数")
    public Result<InstructorClockStatRsqDTO> statAllCount(
            @ApiParam(name = "辅导员用户ID", required = true)
            @RequestParam(name = "instructorId")
            @NotNull(message = "辅导员ID不能为空")
                    Long instructorId
    ) {
        return instructorClockBiz.statAllCount(instructorId);
    }

    @GetMapping("/instructor-clock/stat-by-year-month")
    @ApiOperation(value = "辅导员考勤统计")
    public Result<List<String>> statByYearAndMonth(
        @ApiParam(name = "辅导员用户ID", required = true)
        @RequestParam(name = "instructorId")
        @NotNull(message = "辅导员用户ID不能为空")
            Long instructorId,
        @ApiParam(name = "年份", required = true)
        @RequestParam(name = "year")
        @NotNull(message = "年份不能为空")
        @Min(value = 1000, message = "不合法的年份")
        @Max(value = 9999, message = "不合法的年份")
            Integer year,
        @ApiParam(name = "月份", required = true)
        @RequestParam(name = "month")
        @NotNull(message = "月份不能为空")
        @Min(value = 1, message = "不合法的月份")
        @Max(value = 12, message = "不合法的月份")
            Integer month
    ){
        return null;
    }

    @PostMapping("/instructor-clock")
    @ApiOperation(value = "辅导员打卡")
    public Result instructorClock(@Valid @RequestBody InstructorClockReqDTO req) {
        return instructorClockBiz.instructorClock(req);
    }
}
