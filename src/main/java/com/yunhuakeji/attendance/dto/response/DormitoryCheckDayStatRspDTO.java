package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查寝日统计
 */
public class DormitoryCheckDayStatRspDTO {

    @ApiModelProperty(value = "晚归人数")
    private int stayOutLateNum;

    @ApiModelProperty(value = "到勤人数")
    private int clockNum;

    @ApiModelProperty(value = "未归人数")
    private int stayOutNum;

    @ApiModelProperty(value = "总人数")
    private int totalNum;

    public int getStayOutLateNum() {
        return stayOutLateNum;
    }

    public void setStayOutLateNum(int stayOutLateNum) {
        this.stayOutLateNum = stayOutLateNum;
    }

    public int getClockNum() {
        return clockNum;
    }

    public void setClockNum(int clockNum) {
        this.clockNum = clockNum;
    }

    public int getStayOutNum() {
        return stayOutNum;
    }

    public void setStayOutNum(int stayOutNum) {
        this.stayOutNum = stayOutNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
