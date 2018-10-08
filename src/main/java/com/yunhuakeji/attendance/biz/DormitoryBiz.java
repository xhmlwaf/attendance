package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.DormitoryCheckOverReqDTO;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckDayStatListRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckDayStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckWeekStatListRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckWeekStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitorySimpleRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryStudentStatRspDTO;
import com.yunhuakeji.attendance.dto.response.StudentDormitoryRsqDTO;
import java.util.List;

public interface DormitoryBiz {

  Result<List<BuildingQueryRspDTO>> listAllBuilding();

  Result<List<DormitorySimpleRspDTO>> listDormitory(Long buildingId, Integer floorNumber);

  Result<List<BuildingQueryRspDTO>> listBuildingForApp(Long userId);

  Result<List<DormitorySimpleRspDTO>> listDormitoryForApp(Long userId, Long buildingId,
      Integer floorNumber);

  Result<List<DormitoryClockStatDTO>> listDormitoryClockStatForApp(Long userId,
      Long buildingId,
      Integer floorNumber,
      Long dormitoryId,
      Boolean checkStatus,
      String orderBy,
      String descOrAsc);

  Result<List<DormitoryStudentStatRspDTO>> getDormitoryClockDetailStatForApp(Long userId,
      Long dormitoryId);

  Result<DormitoryCheckDayStatRspDTO> dayStat(Long userId, Integer year, Integer month,
      Integer day);

  Result<List<DormitoryCheckDayStatListRspDTO>> dayStatStudentList(Long userId,
      Integer year,
      Integer month,
      Integer day,
      Byte clockStatus);

  Result<DormitoryCheckWeekStatRspDTO> weekStat(Long userId, Integer weekNumber);

  Result<List<DormitoryCheckWeekStatListRspDTO>> weekStatStudentList(Long userId,
      Integer weekNumber, Byte clockStatus);

  Result addDormitoryCheck(DormitoryCheckOverReqDTO reqDTO);

  Result<List<StudentDormitoryRsqDTO>> queryStudent(Long userId, String nameOrCode);


}
