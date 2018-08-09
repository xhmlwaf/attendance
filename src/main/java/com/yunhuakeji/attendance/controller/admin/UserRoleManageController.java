package com.yunhuakeji.attendance.controller.admin;

import com.yunhuakeji.attendance.biz.UserRoleManageBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.ClearFrequentlyUsedPhoneReqDTO;
import com.yunhuakeji.attendance.dto.request.DormitoryAdminSaveReqDTO;
import com.yunhuakeji.attendance.dto.request.SecondaryCollegeAdminSaveReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentOfficeAdminSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryAdminQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.InstructorManageQueryDTO;
import com.yunhuakeji.attendance.dto.response.OrgBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.OrgQueryTreeRspDTO;
import com.yunhuakeji.attendance.dto.response.SecondaryCollegeAdminQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.StaffBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentOfficeAdminQueryRspDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "用户角色管理模块接口")
@Controller
@Validated
public class UserRoleManageController {

  @Autowired
  private UserRoleManageBiz userRoleManageBiz;

  @GetMapping("/student")
  @ApiOperation(value = "学生分页查询")
  PagedResult<StudentBaseInfoDTO> studentPageQuery(
      @ApiParam(name = "姓名")
      @RequestParam(name = "name", required = false)
          String name,
      @ApiParam(name = "学号")
      @RequestParam(name = "code", required = false)
          String code,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return null;
    //return userRoleManageBiz.studentPageQuery(name, code, pageNo, pageSize);
  }

  @DeleteMapping("/student-phone")
  @ApiOperation(value = "清除常用手机")
  Result clearFrequentlyUsedPhone(@Valid @RequestBody ClearFrequentlyUsedPhoneReqDTO reqDTO) {
    return userRoleManageBiz.clearFrequentlyUsedPhone(reqDTO);
  }

  @GetMapping("/instructor")
  @ApiOperation(value = "辅导员分页查询")
  PagedResult<InstructorManageQueryDTO> instructorPageQuery(
      @ApiParam(name = "姓名")
      @RequestParam(name = "name", required = false)
          String name,
      @ApiParam(name = "工号")
      @RequestParam(name = "code", required = false)
          String code,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return userRoleManageBiz.instructorPageQuery(name, code, pageNo, pageSize);
  }

  @GetMapping("/secondary-college-admin")
  @ApiOperation(value = "二级学院管理员分页查询")
  PagedResult<SecondaryCollegeAdminQueryRspDTO> secondaryCollegeAdminPage(
      @ApiParam(name = "姓名")
      @RequestParam(name = "name", required = false)
          String name,
      @ApiParam(name = "工号")
      @RequestParam(name = "code", required = false)
          String code,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return userRoleManageBiz.secondaryCollegeAdminPage(name, code, pageNo, pageSize);
  }


  @GetMapping("/dormitory-admin")
  @ApiOperation(value = "宿舍管理员分页查询")
  PagedResult<DormitoryAdminQueryRspDTO> dormitoryAdminPage(
      @ApiParam(name = "姓名")
      @RequestParam(name = "name", required = false)
          String name,
      @ApiParam(name = "工号")
      @RequestParam(name = "code", required = false)
          String code,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return userRoleManageBiz.dormitoryAdminPage(name, code, pageNo, pageSize);
  }

  @GetMapping("/student-office-admin")
  @ApiOperation(value = "学生处管理员分页查询")
  PagedResult<StudentOfficeAdminQueryRspDTO> studentOfficeAdminPage(
      @ApiParam(name = "姓名")
      @RequestParam(name = "name", required = false)
          String name,
      @ApiParam(name = "工号")
      @RequestParam(name = "code", required = false)
          String code,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return userRoleManageBiz.studentOfficeAdminPage(name, code, pageNo, pageSize);
  }

  @GetMapping("/org-tree")
  @ApiOperation(value = "机构树查询")
  Result<List<OrgBaseInfoDTO>> orgTreeQuery(

  ) {
    return userRoleManageBiz.orgTreeQuery();
  }

  @GetMapping("/org/{orgId}/staff/all")
  @ApiOperation(value = "根据机构查所属教职工")
  PagedResult<StaffBaseInfoDTO> getStaffListByOrg(
      @ApiParam(name = "机构ID", required = true)
      @PathVariable(name = "orgId")
      @NotNull(message = "机构ID不能为空")
          Long orgId,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize) {
    return userRoleManageBiz.getStaffListByOrg(orgId, pageNo, pageSize);

  }

  @GetMapping("/role/staff/all")
  @ApiOperation(value = "根据角色类型查询全部教职工")
  Result<List<StaffBaseInfoDTO>> getStaffListByRole(
      @ApiParam(name = "角色类型", required = true)
      @RequestParam(name = "roleType")
      @NotNull(message = "角色类型不能为空")
          Long roleType) {
    return null;

  }

  @PostMapping("/student-office-admin")
  @ApiOperation(value = "保存学生处管理员列表")
  Result studentOfficeAdminSave(@ApiParam(name = "教职工ID列表", required = true)
                                @NotNull(message = "教职工ID列表不能为空")
                                @Size(min = 1, max = 1000, message = "教职工ID列表长度1-1000")
                                    List<Long> staffIdList) {
    return null;
  }


  @PostMapping("/dormitory-admin")
  @ApiOperation(value = "保存宿舍管理员列表")
  Result dormitoryAdminSave(@Valid @RequestBody DormitoryAdminSaveReqDTO reqDTO) {
    return null;
  }


  @PostMapping("/secondary-college-admin")
  @ApiOperation(value = "保存二级学院管理员列表")
  Result secondaryCollegeAdminSave(@Valid @RequestBody SecondaryCollegeAdminSaveReqDTO reqDTO) {
    return null;
  }


}
