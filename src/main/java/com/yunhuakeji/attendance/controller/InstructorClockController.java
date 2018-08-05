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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(value = "辅导员考勤操作接口")
@Controller
public class InstructorClockController {

    @Autowired
    private InstructorClockBiz instructorClockBiz;

    @GetMapping("/instructor-clock/stat")
    @ApiOperation(value = "辅导员考勤统计")
    public Result<InstructorClockStatRsqDTO> statByInstructor(
            @ApiParam(name = "辅导员用户ID", required = true)
            @RequestParam(name = "instructorId")
            @NotNull(message = "辅导员ID不能为空")
                    Long instructorId
    ) {
        return instructorClockBiz.statByInstructor(instructorId);
    }

    @PostMapping("/instructor-clock")
    @ApiOperation(value = "辅导员打卡")
    public Result instructorClock(@Valid @RequestBody InstructorClockReqDTO req) {
        return instructorClockBiz.instructorClock(req);
    }
}
