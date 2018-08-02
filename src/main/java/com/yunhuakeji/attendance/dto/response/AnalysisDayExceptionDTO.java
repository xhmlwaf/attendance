package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

import java.util.Date;

public class AnalysisDayExceptionDTO {

    @ApiParam(name = "日期")
    private Date date;
    @ApiParam(name = "未归人数")
    private int stayoutNum;
    @ApiParam(name = "晚归人数")
    private int stayoutLateNum;
}
