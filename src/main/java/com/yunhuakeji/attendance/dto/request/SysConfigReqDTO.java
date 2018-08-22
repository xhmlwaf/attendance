package com.yunhuakeji.attendance.dto.request;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统配置
 */
public class SysConfigReqDTO {

    @ApiModelProperty(value = "打卡开始时间 格式HH:mm:ss", required = true)
    @NotBlank(message = "打卡开始时间不能为空")
    private String clockStartTime;

    @ApiModelProperty(value = "打卡结束时间 格式HH:mm:ss", required = true)
    @NotBlank(message = "打卡结束时间不能为空")
    private String clockEndTime;

    @ApiModelProperty(value = "查寝开始时间 格式HH:mm:ss", required = true)
    @NotBlank(message = "查寝开始时间不能为空")
    private String checkDormStartTime;

    @ApiModelProperty(value = "查寝结束时间 格式HH:mm:ss", required = true)
    @NotBlank(message = "查寝结束时间不能为空")
    private String checkDormEndTime;

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
    private List<AddressReqDTO> addressReqDTOList;

    @ApiModelProperty(value = "是否校验设备", required = true)
    @NotNull(message = "是否校验设备不能为空.1:需要校验 2:不需要校验")
    @Min(value = 1,message = "范围1-2")
    @Max(value = 2,message = "范围1-2")
    private Byte checkDevice;


    public String getClockStartTime() {
        return clockStartTime;
    }

    public void setClockStartTime(String clockStartTime) {
        this.clockStartTime = clockStartTime;
    }

    public String getClockEndTime() {
        return clockEndTime;
    }

    public void setClockEndTime(String clockEndTime) {
        this.clockEndTime = clockEndTime;
    }

    public String getCheckDormStartTime() {
        return checkDormStartTime;
    }

    public void setCheckDormStartTime(String checkDormStartTime) {
        this.checkDormStartTime = checkDormStartTime;
    }

    public String getCheckDormEndTime() {
        return checkDormEndTime;
    }

    public void setCheckDormEndTime(String checkDormEndTime) {
        this.checkDormEndTime = checkDormEndTime;
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

