package com.yunhuakeji.attendance.dto.response;

import com.yunhuakeji.attendance.dao.bizdao.model.ClockAddressSetting;
import com.yunhuakeji.attendance.dto.request.AddressReqDTO;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统配置
 */
public class SysConfigRspDTO {

  @ApiModelProperty(value = "打卡开始时间,格式HH:mm:ss", required = true)
  private String clockStartTime;

  @ApiModelProperty(value = "打卡结束时间,格式HH:mm:ss", required = true)
  private String clockEndTime;

  @ApiModelProperty(value = "查寝开始时间,格式HH:mm:ss", required = true)
  private String checkClockStartTime;

  @ApiModelProperty(value = "查寝结束时间,格式HH:mm:ss", required = true)
  private String checkClockEndTime;

  @ApiModelProperty(value = "地址列表", required = true)
  private List<ClockAddressSetting> clockAddressSettingList;

  @ApiModelProperty(value = "是否校验设备", required = true)
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

  public String getCheckClockStartTime() {
    return checkClockStartTime;
  }

  public void setCheckClockStartTime(String checkClockStartTime) {
    this.checkClockStartTime = checkClockStartTime;
  }

  public String getCheckClockEndTime() {
    return checkClockEndTime;
  }

  public void setCheckClockEndTime(String checkClockEndTime) {
    this.checkClockEndTime = checkClockEndTime;
  }

  public List<ClockAddressSetting> getClockAddressSettingList() {
    return clockAddressSettingList;
  }

  public void setClockAddressSettingList(List<ClockAddressSetting> clockAddressSettingList) {
    this.clockAddressSettingList = clockAddressSettingList;
  }

  public Byte getCheckDevice() {
    return checkDevice;
  }

  public void setCheckDevice(Byte checkDevice) {
    this.checkDevice = checkDevice;
  }

}

