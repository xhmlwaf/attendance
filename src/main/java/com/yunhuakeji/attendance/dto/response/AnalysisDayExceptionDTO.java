package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AnalysisDayExceptionDTO {

    @ApiModelProperty(name = "日期")
    private Date date;
    @ApiModelProperty(name = "未归人数")
    private int stayoutNum;
    @ApiModelProperty(name = "晚归人数")
    private int stayoutLateNum;
}
