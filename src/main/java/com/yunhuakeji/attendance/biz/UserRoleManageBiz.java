package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.ClearFrequentlyUsedPhoneReqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorManageQueryDTO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;

public interface UserRoleManageBiz {

  PagedResult<StudentBaseInfoDTO> studentPageQuery(String name, String code, Integer pageNo, Integer pageSize);

  Result clearFrequentlyUsedPhone(ClearFrequentlyUsedPhoneReqDTO reqDTO);

  PagedResult<InstructorManageQueryDTO> instructorPageQuery(String name, String code, Integer pageNo, Integer pageSize);

}
