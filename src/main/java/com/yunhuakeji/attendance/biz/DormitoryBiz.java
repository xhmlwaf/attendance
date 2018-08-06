package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.DormitoryBuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryQueryRspDTO;

import java.util.List;

public interface DormitoryBiz {

  Result<List<DormitoryBuildingQueryRspDTO>> listAllBuilding();

  Result<List<DormitoryQueryRspDTO>> listDormitory(Long buildingId, Integer floorNumber);

}
