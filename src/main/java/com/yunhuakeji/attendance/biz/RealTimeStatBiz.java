package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.ClockStatByBuildingRspDTO;

import java.util.List;

public interface RealTimeStatBiz {

    Result<List<ClockStatByBuildingRspDTO>> realTimeStatByBuilding();
}
