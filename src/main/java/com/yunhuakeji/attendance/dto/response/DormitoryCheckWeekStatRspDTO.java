package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class DormitoryCheckWeekStatRspDTO {

    @ApiModelProperty(value = "晚归人数")
    private int stayOutLateNum=0;

    @ApiModelProperty(value = "未归人数")
    private int stayOutNum=0;

    @ApiModelProperty(value = "总人数")
    private int totalNum;

    public int getStayOutLateNum() {
        return stayOutLateNum;
    }

    public void setStayOutLateNum(int stayOutLateNum) {
        this.stayOutLateNum = stayOutLateNum;
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
