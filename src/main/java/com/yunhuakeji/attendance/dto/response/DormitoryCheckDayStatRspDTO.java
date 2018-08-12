package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查寝日统计
 */
public class DormitoryCheckDayStatRspDTO {

    @ApiModelProperty(name = "晚归人数")
    private int stayOutLateNum;

    @ApiModelProperty(name = "到勤人数")
    private int clockNum;

    @ApiModelProperty(name = "未归人数")
    private int stayOutNum;

    @ApiModelProperty(name = "总人数")
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
