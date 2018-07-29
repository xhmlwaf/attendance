package com.yunhuakeji.attendance.controller;

import java.util.List;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.controller.dto.response.StudentClockHistoryQueryRspDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api(value = "学生考勤历史操作接口")
@Controller
public class StudentClockHistoryController {

    @GetMapping("/student-clock-history")
    @ApiOperation(value = "根据学生ID和日期查询全部历史")
    public Result<List<StudentClockHistoryQueryRspDTO>> listAll(
            @ApiParam(name = "学生ID", required = true)
            @RequestParam(name = "id")
            @NotBlank(message = "学生ID不能为空")
                    String id,
            @ApiParam(name = "年份", required = true)
            @RequestParam(name = "year")
            @NotNull(message = "年份不能为空")
                    Integer year,
            @ApiParam(name = "月份", required = true)
            @RequestParam(name = "month")
            @NotNull(message = "月份不能为空")
                    Integer month
    ) {

        return null;
    }
}
