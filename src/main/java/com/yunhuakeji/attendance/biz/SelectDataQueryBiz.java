package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.CollegeBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.InstructorQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.MajorQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.UserBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.WeekInfoRspDTO;
import java.util.List;

public interface SelectDataQueryBiz {

  Result<List<BuildingQueryRspDTO>> listAllBuilding();

  Result<List<CollegeBaseInfoDTO>> listAllSecondaryCollege();

  Result<List<WeekInfoRspDTO>> listAllWeekInfo();

  Result<List<MajorQueryRspDTO>> listAllMajorInfo(Long orgId);

  Result<List<InstructorQueryRspDTO>> listInstructor(Long orgId, Long majorId, Long userId);

  Result<List<CollegeBaseInfoDTO>> listByUserId(Long userId);

  Result<UserBaseInfoDTO> getUserBasicInfo(Long userId);

  Result<Integer> getCurrWeekNum();
}
