package com.yunhuakeji.attendance.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统配置
 */
public class SysConfigReqDTO {

    @ApiModelProperty(value = "打卡开始时间", required = true)
    @NotNull(message = "打卡开始时间不能为空")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date clockStartTime;

    @ApiModelProperty(value = "打卡结束时间", required = true)
    @NotNull(message = "打卡结束时间不能为空")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date clockEndTime;

    @ApiModelProperty(value = "查寝开始时间", required = true)
    @NotNull(message = "查寝开始时间不能为空")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date checkDormStartTime;

    @ApiModelProperty(value = "查寝结束时间", required = true)
    @NotNull(message = "查寝结束时间不能为空")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date checkDormkEndTime;

    @ApiModelProperty(value = "年", required = true)
    @NotNull(message = "年不能为空")
    private Integer year;

    @ApiModelProperty(value = "月", required = true)
    @NotNull(message = "月不能为空")
    private Integer month;

    @ApiModelProperty(value = "日期列表", required = true)
    private List<Integer> dayList;

    @ApiModelProperty(value = "地址列表", required = true)
    @Size(min = 1,max = 100,message = "地址个数在1-100")
    @NotNull(message = "地址列表不能为空")
    private List<AddressReqDTO> addressReqDTOList;

    @ApiModelProperty(value = "是否校验设备", required = true)
    @NotNull(message = "是否校验设备不能为空.1:需要校验 2:不需要校验")
    private Byte checkDevice;

    public Date getClockStartTime() {
        return clockStartTime;
    }

    public void setClockStartTime(Date clockStartTime) {
        this.clockStartTime = clockStartTime;
    }

    public Date getClockEndTime() {
        return clockEndTime;
    }

    public void setClockEndTime(Date clockEndTime) {
        this.clockEndTime = clockEndTime;
    }

    public Date getCheckDormStartTime() {
        return checkDormStartTime;
    }

    public void setCheckDormStartTime(Date checkDormStartTime) {
        this.checkDormStartTime = checkDormStartTime;
    }

    public Date getCheckDormkEndTime() {
        return checkDormkEndTime;
    }

    public void setCheckDormkEndTime(Date checkDormkEndTime) {
        this.checkDormkEndTime = checkDormkEndTime;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public List<Integer> getDayList() {
        return dayList;
    }

    public void setDayList(List<Integer> dayList) {
        this.dayList = dayList;
    }

    public List<AddressReqDTO> getAddressReqDTOList() {
        return addressReqDTOList;
    }

    public void setAddressReqDTOList(List<AddressReqDTO> addressReqDTOList) {
        this.addressReqDTOList = addressReqDTOList;
    }

    public Byte getCheckDevice() {
        return checkDevice;
    }

    public void setCheckDevice(Byte checkDevice) {
        this.checkDevice = checkDevice;
    }
}

