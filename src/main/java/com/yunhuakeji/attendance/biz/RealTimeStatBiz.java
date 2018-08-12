package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.ClockStatByBuildingRspDTO;
import com.yunhuakeji.attendance.dto.response.ClockStatByStudentRspDTO;

import java.util.List;

public interface RealTimeStatBiz {

    Result<List<ClockStatByBuildingRspDTO>> realTimeStatByBuilding();

    PagedResult<ClockStatByStudentRspDTO> realTimeStatByStudent(Integer pageNo, Integer pageSize);
}
