package com.yunhuakeji.attendance.controller.analysis;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByDayRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByDayRsqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Api(value = "晚归考勤分析模块接口")
@Controller
public class AnalysisController {


    @GetMapping("/analysis/exeception-stat-by-day")
    @ApiOperation(value = "每日异常数据统计")
    public Result<AnalysisExceptionStatByDayRsqDTO> getAnalysisExceptionStatByDay(
            @ApiParam(name = "机构ID")
            @RequestParam(name = "orgId")
                    Long orgId,

            @ApiParam(name = "日期", required = true)
            @RequestParam(name = "date")
            @NotNull(message = "日期不能为空")
                    Date date
    ) {

        return null;
    }

    @GetMapping("/analysis/exeception-clock-by-day")
    @ApiOperation(value = "每日异常数据分页查询")
    public PagedResult<AnalysisExceptionClockByDayRsqDTO> getAnalysisExceptionClockByDay(
            @ApiParam(name = "机构ID")
            @RequestParam(name = "orgId")
                    Long orgId,
            @ApiParam(name = "专业ID")
            @RequestParam(name = "majorId", required = false)
                    Long majorId,
            @ApiParam(name = "辅导员ID")
            @RequestParam(name = "instructor", required = false)
                    Long instructor,
            @ApiParam(name = "状态")
            @RequestParam(name = "clockStatus", required = false)
                    Byte clockStatus
    ) {
        return null;
    }




}
