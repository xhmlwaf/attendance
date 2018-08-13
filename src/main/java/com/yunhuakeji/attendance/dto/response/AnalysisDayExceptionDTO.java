package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AnalysisDayExceptionDTO {

    @ApiModelProperty(value = "日期")
    private Date date;
    @ApiModelProperty(value = "未归人数")
    private int stayoutNum;
    @ApiModelProperty(value = "晚归人数")
    private int stayoutLateNum;
}
