package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.BigScreenMonitorStatRspDTO;

public interface BigScreenMonitorBiz {

  Result<BigScreenMonitorStatRspDTO> getBigScreenMonitorStat();

  Result<String> getCopyWriting();

  Result<String> getQrcodeImg();
}
