package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.controller.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.controller.dto.response.InstructorClockStatRsqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotBlank;

@Api(value = "辅导员考勤操作接口")
@Controller
public class InstructorClockController {

    @GetMapping("/instructor-clock/{id}")
    @ApiOperation(value = "辅导员考勤统计")
    public Result<InstructorClockStatRsqDTO> getStudentBaseInfo(
            @ApiParam(name = "辅导员用户ID", required = true)
            @PathVariable(name = "id")
            @NotBlank(message = "辅导员用户不能为空")
                    String id
    ) {

        return null;
    }

    @PostMapping("/instructor-clock")
    @ApiOperation(value = "辅导员打卡")
    public Result<Boolean> clock(InstructorClockReqDTO req) {

        return null;
    }
}
