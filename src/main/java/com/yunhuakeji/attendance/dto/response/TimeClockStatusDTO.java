package com.yunhuakeji.attendance.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TimeClockStatusDTO {

    @ApiModelProperty(value = "最后更新时间")
    @JsonFormat(pattern = "yyyy年M月d日 HH:mm:ss", timezone = "GMT+8")
    private Date clockDate;
    @ApiModelProperty(value = "打卡状态 状态枚举 1未打卡，2到勤，3晚归，4未归")
    private byte clockStatus;

    public Date getClockDate() {
        return clockDate;
    }

    public void setClockDate(Date clockDate) {
        this.clockDate = clockDate;
    }

    public byte getClockStatus() {
        return clockStatus;
    }

    public void setClockStatus(byte clockStatus) {
        this.clockStatus = clockStatus;
    }
}
