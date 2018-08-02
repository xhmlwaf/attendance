package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.DormCheckReqDTO;
import com.yunhuakeji.attendance.dto.response.DormCheckDayStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormCheckQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DromCheckDetailQueryRspDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api(value = "查寝操作接口")
@Controller
public class DormCheckController {


    @GetMapping("/dorm-check")
    @ApiOperation(value = "查寝结果")
    public Result<DormCheckQueryRspDTO> listALl(
            @ApiParam(name = "用户ID", required = true)
            @RequestParam(name = "userId")
            @NotBlank(message = "用户ID不能为空")
                    String userId
    ) {
        return null;
    }

    @GetMapping("/dorm-check-detail")
    @ApiOperation(value = "查寝详情结果")
    public Result<DromCheckDetailQueryRspDTO> getDormCheckDetail(
            @ApiParam(name = "宿舍", required = true)
            @RequestParam(name = "dormId")
            @NotBlank(message = "宿舍ID不能为空")
                    String dormId
    ) {
        return null;
    }


    @PostMapping("/dorm-check")
    @ApiOperation(value = "查寝")
    public Result dormCheck(DormCheckReqDTO reqDTO) {
        return null;
    }

    @PostMapping("/dorm-check-day-stat")
    @ApiOperation(value = "查寝日统计")
    public Result<DormCheckDayStatRspDTO> dayStat(
            @ApiParam(name = "用户ID", required = true)
            @RequestParam(name = "userId")
            @NotBlank(message = "用户ID不能为空")
                    String userId,
            @ApiParam(name = "年份", required = true)
            @RequestParam(name = "year")
            @NotNull(message = "年份不能为空")
                    Integer year,
            @ApiParam(name = "月份", required = true)
            @RequestParam(name = "month")
            @NotNull(message = "月份不能为空")
                    Integer month,
            @ApiParam(name = "日", required = true)
            @RequestParam(name = "day")
            @NotNull(message = "日不能为空")
                    Integer day
    ) {
        return null;
    }


}
