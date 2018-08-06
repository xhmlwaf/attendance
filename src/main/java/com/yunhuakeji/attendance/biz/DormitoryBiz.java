package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryQueryRspDTO;

import java.util.List;

public interface DormitoryBiz {

    Result<List<BuildingQueryRspDTO>> listAllBuilding();

    Result<List<DormitoryQueryRspDTO>> listDormitory(Long buildingId, Integer floorNumber);

    Result<List<BuildingQueryRspDTO>> listForApp(Long userId, Byte appType);

}
