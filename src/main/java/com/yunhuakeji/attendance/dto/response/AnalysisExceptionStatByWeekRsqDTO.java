package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 按日异常统计
 */
public class AnalysisExceptionStatByWeekRsqDTO {

    @ApiModelProperty(value = "本周未归人数")
    private int weekStayoutNum=0;
    @ApiModelProperty(value = "本周晚归人数")
    private int weekStayoutLateNum=0;
    @ApiModelProperty(value = "正常人数")
    private int weekNormalNum=0;

    public int getWeekStayoutNum() {
        return weekStayoutNum;
    }

    public void setWeekStayoutNum(int weekStayoutNum) {
        this.weekStayoutNum = weekStayoutNum;
    }

    public int getWeekStayoutLateNum() {
        return weekStayoutLateNum;
    }

    public void setWeekStayoutLateNum(int weekStayoutLateNum) {
        this.weekStayoutLateNum = weekStayoutLateNum;
    }

    public int getWeekNormalNum() {
        return weekNormalNum;
    }

    public void setWeekNormalNum(int weekNormalNum) {
        this.weekNormalNum = weekNormalNum;
    }
}
