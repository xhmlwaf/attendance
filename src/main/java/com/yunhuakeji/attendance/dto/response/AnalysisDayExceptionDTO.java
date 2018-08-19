package com.yunhuakeji.attendance.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AnalysisDayExceptionDTO {

    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
    @ApiModelProperty(value = "未归人数")
    private int stayoutNum;
    @ApiModelProperty(value = "晚归人数")
    private int stayoutLateNum;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStayoutNum() {
        return stayoutNum;
    }

    public void setStayoutNum(int stayoutNum) {
        this.stayoutNum = stayoutNum;
    }

    public int getStayoutLateNum() {
        return stayoutLateNum;
    }

    public void setStayoutLateNum(int stayoutLateNum) {
        this.stayoutLateNum = stayoutLateNum;
    }
}
