package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.*;

import java.util.List;

public interface SelectDataQueryBiz {

    Result<List<BuildingQueryRspDTO>> listAllBuilding();

    Result<List<CollegeBaseInfoDTO>> listAllSecondaryCollege();

    Result<List<WeekInfoRspDTO>> listAllWeekInfo();

    Result<List<MajorQueryRspDTO>> listAllMajorInfo(Long orgId);

    Result<List<InstructorQueryRspDTO>> listInstructor(Long orgId, Long majorId);

    Result<List<CollegeBaseInfoDTO>> listByUserId(Long userId);

    Result<UserBaseInfoDTO> getUserBasicInfo(Long userId);

    Result<Integer> getCurrWeekNum();
}
