package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.ClearFrequentlyUsedPhoneReqDTO;
import com.yunhuakeji.attendance.dto.response.*;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;

public interface UserRoleManageBiz {

    PagedResult<StudentBaseInfoDTO> studentPageQuery(String name, String code, Integer pageNo, Integer pageSize);

    Result clearFrequentlyUsedPhone(ClearFrequentlyUsedPhoneReqDTO reqDTO);

    PagedResult<InstructorManageQueryDTO> instructorPageQuery(String name, String code, Integer pageNo, Integer pageSize);

    PagedResult<SecondaryCollegeAdminQueryRspDTO> secondaryCollegeAdminPage(String name, String code, Integer pageNo, Integer pageSize);

    PagedResult<DormitoryAdminQueryRspDTO> dormitoryAdminPage(String name, String code, Integer pageNo, Integer pageSize);

    PagedResult<StudentOfficeAdminQueryRspDTO> studentOfficeAdminPage(String name, String code, Integer pageNo, Integer pageSize);

}
