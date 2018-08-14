package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.DormitoryCheckOverReqDTO;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckDayStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckWeekStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockDetailStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitorySimpleRspDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface DormitoryBiz {

  Result<List<BuildingQueryRspDTO>> listAllBuilding();

  Result<List<DormitorySimpleRspDTO>> listDormitory(Long buildingId, Integer floorNumber);

  Result<List<BuildingQueryRspDTO>> listBuildingForApp(Long userId);

  Result<List<DormitorySimpleRspDTO>> listDormitoryForApp(Long userId, Long buildingId, Integer floorNumber);

  Result<List<DormitoryClockStatDTO>> listDormitoryClockStatForApp(Long userId, Long buildingId, Integer floorNumber, Long dormitoryId);

  Result<DormitoryClockDetailStatDTO> getDormitoryClockDetailStatForApp(Long userId, Long dormitoryId);

  Result<DormitoryCheckDayStatRspDTO> dayStat(Long userId, Integer year, Integer month, Integer day);

  Result<DormitoryCheckWeekStatRspDTO> weekStat(Long userId, Integer weekNumber);

  Result addDormitoryCheck(DormitoryCheckOverReqDTO reqDTO);


}
