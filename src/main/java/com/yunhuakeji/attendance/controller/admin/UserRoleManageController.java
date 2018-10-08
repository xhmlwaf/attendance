package com.yunhuakeji.attendance.controller.admin;

import com.alibaba.fastjson.JSON;
import com.yunhuakeji.attendance.biz.UserRoleManageBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.ClearFrequentlyUsedPhoneReqDTO;
import com.yunhuakeji.attendance.dto.request.DeleteAccountReqDTO;
import com.yunhuakeji.attendance.dto.request.DormitoryAdminSaveReqDTO;
import com.yunhuakeji.attendance.dto.request.SecondaryCollegeAdminSaveReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentOfficeAdminSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryAdminQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.InstructorManageQueryDTO;
import com.yunhuakeji.attendance.dto.response.OrgBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.SecondaryCollegeAdminQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.StaffBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentOfficeAdminQueryRspDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户角色管理模块接口")
@RestController
@Validated
public class UserRoleManageController {

  private static final Logger logger = LoggerFactory.getLogger(UserRoleManageController.class);

  @Autowired
  private UserRoleManageBiz userRoleManageBiz;

  @GetMapping("/user-role-manage/student")
  @ApiOperation(value = "学生分页查询")
  PagedResult<StudentBaseInfoDTO> studentPageQuery(
      @ApiParam(value = "姓名或学/工号（姓名或学/工号不为空时将忽略其他查询条件）")
      @RequestParam(name = "nameOrCode", required = false)
          String nameOrCode,
      @ApiParam(value = "页码")
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @ApiParam(value = "页大小")
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return userRoleManageBiz.studentPageQuery(nameOrCode, pageNo, pageSize);
  }

  @DeleteMapping("/user-role-manage/student-phone")
  @ApiOperation(value = "清除常用手机")
  Result clearFrequentlyUsedPhone(@Valid @RequestBody ClearFrequentlyUsedPhoneReqDTO reqDTO) {
    return userRoleManageBiz.clearFrequentlyUsedPhone(reqDTO);
  }

  @GetMapping("/user-role-manage/instructor")
  @ApiOperation(value = "辅导员分页查询")
  PagedResult<InstructorManageQueryDTO> instructorPageQuery(
      @ApiParam(value = "姓名或学/工号（姓名或学/工号不为空时将忽略其他查询条件）")
      @RequestParam(name = "nameOrCode", required = false)
          String nameOrCode,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return userRoleManageBiz.instructorPageQuery(nameOrCode, pageNo, pageSize);
  }

  @GetMapping("/user-role-manage/secondary-college-admin")
  @ApiOperation(value = "二级学院管理员分页查询")
  PagedResult<SecondaryCollegeAdminQueryRspDTO> secondaryCollegeAdminPage(
      @ApiParam(value = "姓名或学/工号（姓名或学/工号不为空时将忽略其他查询条件）")
      @RequestParam(name = "nameOrCode", required = false)
          String nameOrCode,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return userRoleManageBiz.secondaryCollegeAdminPage(nameOrCode, pageNo, pageSize);
  }


  @GetMapping("/user-role-manage/dormitory-admin")
  @ApiOperation(value = "宿舍管理员分页查询")
  PagedResult<DormitoryAdminQueryRspDTO> dormitoryAdminPage(
      @ApiParam(value = "姓名或学/工号（姓名或学/工号不为空时将忽略其他查询条件）")
      @RequestParam(name = "nameOrCode", required = false)
          String nameOrCode,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return userRoleManageBiz.dormitoryAdminPage(nameOrCode, pageNo, pageSize);
  }


  @GetMapping("/user-role-manage/student-office-admin")
  @ApiOperation(value = "学生处管理员分页查询")
  PagedResult<StudentOfficeAdminQueryRspDTO> studentOfficeAdminPage(
      @ApiParam(value = "姓名或学/工号（姓名或学/工号不为空时将忽略其他查询条件）")
      @RequestParam(name = "nameOrCode", required = false)
          String nameOrCode,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize
  ) {
    return userRoleManageBiz.studentOfficeAdminPage(nameOrCode, pageNo, pageSize);
  }

  @GetMapping("/user-role-manage/org-tree")
  @ApiOperation(value = "机构树查询")
  Result<List<OrgBaseInfoDTO>> orgTreeQuery(

  ) {
    return userRoleManageBiz.orgTreeQuery();
  }

  @GetMapping("/user-role-manage/org/{orgId}/staff")
  @ApiOperation(value = "根据机构查所属教职工")
  PagedResult<StaffBaseInfoDTO> getStaffListByOrg(
      @ApiParam(value = "机构ID", required = true)
      @PathVariable(name = "orgId")
      @NotNull(message = "机构ID不能为空")
          Long orgId,
      @RequestParam(value = "pageNo", required = false, defaultValue = "1")
      @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10")
      @Min(value = 1, message = "每页数量最小为1") Integer pageSize) {
    return userRoleManageBiz.getStaffListByOrg(orgId, pageNo, pageSize);

  }

  @GetMapping("/user-role-manage/staff/all")
  @ApiOperation(value = "根据角色类型查询全部教职工")
  Result<List<StaffBaseInfoDTO>> getStaffListByRole(
      @ApiParam(value = "角色类型 1:二级院校管理员,2:宿舍管理员,3:学生处管理员", required = true)
      @RequestParam(name = "roleType")
      @NotNull(message = "角色类型不能为空")
          Byte roleType) {
    return userRoleManageBiz.getStaffListByRole(roleType);

  }

  @PutMapping("/user-role-manage/delete-account")
  @ApiOperation(value = "删除账号")
  Result deleteAccount(@Valid @RequestBody DeleteAccountReqDTO reqDTO) {
    logger.info("params:" + JSON.toJSONString(reqDTO));
    return userRoleManageBiz.deleteAccount(reqDTO);
  }


  @PostMapping("/student-office-admin")
  @ApiOperation(value = "保存学生处管理员列表")
  Result studentOfficeAdminSave(@Valid @RequestBody StudentOfficeAdminSaveReqDTO reqDTO) {
    logger.info("params:" + JSON.toJSONString(reqDTO));
    return userRoleManageBiz.studentOfficeAdminSave(reqDTO);
  }


  @PostMapping("/dormitory-admin")
  @ApiOperation(value = "保存宿舍管理员列表")
  Result dormitoryAdminSave(@Valid @RequestBody DormitoryAdminSaveReqDTO reqDTO) {
    logger.info("params:" + JSON.toJSONString(reqDTO));
    return userRoleManageBiz.dormitoryAdminSave(reqDTO);
  }


  @PostMapping("/secondary-college-admin")
  @ApiOperation(value = "保存二级学院管理员列表")
  Result secondaryCollegeAdminSave(@Valid @RequestBody SecondaryCollegeAdminSaveReqDTO reqDTO) {
    logger.info("params:" + JSON.toJSONString(reqDTO));
    return userRoleManageBiz.secondaryCollegeAdminSave(reqDTO);
  }


}
