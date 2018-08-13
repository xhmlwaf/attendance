package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class StudentClockCareStatRspDTO extends StudentBaseInfoDTO {

    @ApiModelProperty(value = "累计晚归次数")
    private int totalStayOutLate;

    @ApiModelProperty(value = "累计未归次数")
    private int totalStayOut;

    @ApiModelProperty(value = "累计被关怀次数")
    private int totalCared;

    public int getTotalStayOutLate() {
        return totalStayOutLate;
    }

    public void setTotalStayOutLate(int totalStayOutLate) {
        this.totalStayOutLate = totalStayOutLate;
    }

    public int getTotalStayOut() {
        return totalStayOut;
    }

    public void setTotalStayOut(int totalStayOut) {
        this.totalStayOut = totalStayOut;
    }

    public int getTotalCared() {
        return totalCared;
    }

    public void setTotalCared(int totalCared) {
        this.totalCared = totalCared;
    }
}
