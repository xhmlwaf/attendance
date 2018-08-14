package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.ClearFrequentlyUsedPhoneReqDTO;
import com.yunhuakeji.attendance.dto.request.DeleteAccountReqDTO;
import com.yunhuakeji.attendance.dto.request.DormitoryAdminSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryAdminQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.InstructorManageQueryDTO;
import com.yunhuakeji.attendance.dto.response.OrgBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.SecondaryCollegeAdminQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.StaffBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentOfficeAdminQueryRspDTO;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;

public interface UserRoleManageBiz {

  PagedResult<StudentBaseInfoDTO> studentPageQuery(String nameOrCode, Integer pageNo, Integer pageSize);

  Result clearFrequentlyUsedPhone(ClearFrequentlyUsedPhoneReqDTO reqDTO);

  PagedResult<InstructorManageQueryDTO> instructorPageQuery(String nameOrCode, Integer pageNo, Integer pageSize);

  PagedResult<SecondaryCollegeAdminQueryRspDTO> secondaryCollegeAdminPage(String nameOrCode, Integer pageNo, Integer pageSize);

  PagedResult<DormitoryAdminQueryRspDTO> dormitoryAdminPage(String nameOrCode, Integer pageNo, Integer pageSize);

  PagedResult<StudentOfficeAdminQueryRspDTO> studentOfficeAdminPage(String nameOrCode, Integer pageNo, Integer pageSize);

  Result<List<OrgBaseInfoDTO>> orgTreeQuery();

  PagedResult<StaffBaseInfoDTO> getStaffListByOrg(Long orgId, Integer pageNo, Integer pageSize);

  Result<StudentBaseInfoDTO> getStudentBaseInfo(long studentId);

  Result<List<StaffBaseInfoDTO>> getStaffListByRole(byte roleType);

  Result deleteAccount(DeleteAccountReqDTO reqDTO);

  Result studentOfficeAdminSave(List<Long> staffIdList);

  Result dormitoryAdminSave(DormitoryAdminSaveReqDTO reqDTO);

}
