package com.yunhuakeji.attendance.biz.impl;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.EMPTY_MAP;
import static java.util.stream.Collectors.groupingBy;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.biz.CommonBiz;
import com.yunhuakeji.attendance.biz.CommonHandlerUtil;
import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.UserRoleManageBiz;
import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.DormitoryCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.constants.ConfigConstants;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryUser;
import com.yunhuakeji.attendance.dao.basedao.model.InstructorInfo;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.dao.basedao.model.UserOrg;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dao.bizdao.model.AccountBaseInfoDO;
import com.yunhuakeji.attendance.dao.bizdao.model.UserBuildingRef;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.dto.request.ClearFrequentlyUsedPhoneReqDTO;
import com.yunhuakeji.attendance.dto.request.DeleteAccountReqDTO;
import com.yunhuakeji.attendance.dto.request.DormitoryAdminRelationDTO;
import com.yunhuakeji.attendance.dto.request.DormitoryAdminSaveReqDTO;
import com.yunhuakeji.attendance.dto.request.SecondaryCollegeAdminRelationDTO;
import com.yunhuakeji.attendance.dto.request.SecondaryCollegeAdminSaveReqDTO;
import com.yunhuakeji.attendance.dto.request.StudentOfficeAdminSaveReqDTO;
import com.yunhuakeji.attendance.dto.response.BuildingBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.CollegeBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryAdminQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.InstructorManageQueryDTO;
import com.yunhuakeji.attendance.dto.response.OrgBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.SecondaryCollegeAdminQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.StaffBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentOfficeAdminQueryRspDTO;
import com.yunhuakeji.attendance.enums.RoleType;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.baseservice.DormitoryUserService;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.service.baseservice.UserOrgService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.AccountService;
import com.yunhuakeji.attendance.service.bizservice.StudentDeviceRefService;
import com.yunhuakeji.attendance.service.bizservice.UserBuildingService;
import com.yunhuakeji.attendance.service.bizservice.UserOrgRefService;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.PasswordUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserRoleManageBizImpl implements UserRoleManageBiz {

  private static final Logger logger = LoggerFactory.getLogger(UserRoleManageBizImpl.class);

  @Autowired
  private UserService userService;

  @Autowired
  private DormitoryUserService dormitoryUserService;

  @Autowired
  private DormitoryCacheService dormitoryCacheService;

  @Autowired
  private BuildingCacheService buildingCacheService;

  @Autowired
  private UserClassService userClassService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private MajorCacheService majorCacheService;

  @Autowired
  private OrgCacheService orgCacheService;

  @Autowired
  private StudentDeviceRefService studentDeviceRefService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private UserOrgRefService userOrgRefService;

  @Autowired
  private UserBuildingService userBuildingService;

  @Autowired
  private UserOrgService userOrgService;

  @Override
  public PagedResult<StudentBaseInfoDTO> studentPageQuery(String nameOrCode, Integer pageNo,
      Integer pageSize) {
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    PageInfo pageInfo = userService.getStudentForPage(nameOrCode, pageNo, pageSize);
    List<User> userList = pageInfo.getList();

    List<StudentBaseInfoDTO> studentBaseInfoDTOList = new ArrayList<>();
    List<Long> instructorIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(userList)) {
      //获取批量UserIds
      List<Long> userIds = getUserIds(userList);

      List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByUserIds(userIds);
      Map<Long, DormitoryUser> userToDormitoryMap = getUserToDormitoryMap(dormitoryUserList);
      Map<Long, DormitoryInfo> dormitoryInfoMap = dormitoryCacheService.getDormitoryMap();
      Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();
      List<UserClass> userClassList = userClassService.listByUserIds(userIds);
      Map<Long, Long> userClassMap = getUserClassMap(userClassList);
      Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();
      Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
      Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();

      for (User user : userList) {
        StudentBaseInfoDTO dto = new StudentBaseInfoDTO();
        dto.setStudentCode(user.getCode());
        DormitoryUser dormitoryUser = userToDormitoryMap.get(user.getUserId());
        setDormitoryAndBuildingInfo(dormitoryInfoMap, buildingInfoMap, dto, dormitoryUser);
        dto.setClassId(userClassMap.get(user.getUserId()));
        ClassInfo classInfo = classInfoMap.get(userClassMap.get(user.getUserId()));
        CommonBiz
            .setMajorAndCollegeInfo(instructorIds, majorInfoMap, collegeInfoMap, dto, classInfo);
        dto.setProfilePhoto(user.getHeadPortraitPath());
        dto.setStudentId(user.getUserId());
        dto.setStudentName(user.getUserName());
        studentBaseInfoDTOList.add(dto);
      }
    }

    if (!CollectionUtils.isEmpty(instructorIds) && !CollectionUtils
        .isEmpty(studentBaseInfoDTOList)) {
      List<User> instructorList = userService.selectByPrimaryKeyList(instructorIds);
      Map<Long, User> instructorMap = getInstructorMap(instructorList);
      for (StudentBaseInfoDTO dto : studentBaseInfoDTOList) {
        User user = instructorMap.get(dto.getInstructorId());
        dto.setInstructorName(user.getUserName());
      }
    }

    //3.组装返回结果
    Page<StudentBaseInfoDTO> studentBaseInfoDTOPage = new Page<>();
    studentBaseInfoDTOPage.setResult(studentBaseInfoDTOList);
    studentBaseInfoDTOPage.setTotalCount((int) pageInfo.getTotal());
    studentBaseInfoDTOPage.setPageSize(pageSize);
    studentBaseInfoDTOPage.setPageNo(pageNo);
    studentBaseInfoDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(studentBaseInfoDTOPage);
  }


  private void setDormitoryAndBuildingInfo(Map<Long, DormitoryInfo> dormitoryInfoMap,
      Map<Long, BuildingInfo> buildingInfoMap, StudentBaseInfoDTO dto,
      DormitoryUser dormitoryUser) {
    if (dormitoryUser != null) {
      dto.setDormitoryId(dormitoryUser.getDormitoryId());
      dto.setBedCode(dormitoryUser.getBedCode());
      DormitoryInfo dormitoryInfo = dormitoryInfoMap.get(dormitoryUser.getDormitoryId());
      CommonBiz.setDormitoryAndBuilding(buildingInfoMap, dto, dormitoryInfo);
    }
  }


  @Override
  public Result clearFrequentlyUsedPhone(ClearFrequentlyUsedPhoneReqDTO reqDTO) {
    studentDeviceRefService.deleteByStudentIds(reqDTO.getStudentIds());
    return Result.success(null);
  }

  @Override
  public PagedResult<InstructorManageQueryDTO> instructorPageQuery(String nameOrCode,
      Integer pageNo, Integer pageSize) {
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    PageInfo pageInfo = userClassService.listInstructorInfo(nameOrCode, pageNo, pageSize);
    List<InstructorInfo> instructorInfoList = pageInfo.getList();

    List<InstructorManageQueryDTO> instructorManageQueryDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(instructorInfoList)) {
      Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
      Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();
      Map<Long, ClassInfo> classInstructorMap = classCacheService.getInstructorClassInfoMap();
      for (InstructorInfo instructorInfo : instructorInfoList) {
        InstructorManageQueryDTO dto = new InstructorManageQueryDTO();
        dto.setUserId(instructorInfo.getUserId());
        dto.setName(instructorInfo.getName());
        dto.setCode(instructorInfo.getCode());
        ClassInfo classInfo = classInstructorMap.get(instructorInfo.getUserId());
        if (classInfo != null) {
          MajorInfo majorInfo = majorInfoMap.get(classInfo.getMajorId());
          if (majorInfo != null) {
            CollegeInfo collegeInfo = collegeInfoMap.get(majorInfo.getOrgId());
            dto.setCollegeId(majorInfo.getOrgId());
            if (collegeInfo != null) {
              dto.setCollegeName(collegeInfo.getName());
            }
          }
        }
        instructorManageQueryDTOList.add(dto);
      }
    }

    //3.组装返回结果
    Page<InstructorManageQueryDTO> instructorManageQueryDTOPage = new Page<>();
    instructorManageQueryDTOPage.setResult(instructorManageQueryDTOList);
    instructorManageQueryDTOPage.setTotalCount((int) pageInfo.getTotal());
    instructorManageQueryDTOPage.setPageSize(pageSize);
    instructorManageQueryDTOPage.setPageNo(pageNo);
    instructorManageQueryDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(instructorManageQueryDTOPage);
  }

  @Override
  public PagedResult<SecondaryCollegeAdminQueryRspDTO> secondaryCollegeAdminPage(String nameOrCode,
      Integer pageNo, Integer pageSize) {
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    PageInfo pageInfo = accountService.secondaryCollegeAdminPageQuery(nameOrCode, pageNo, pageSize);
    List<AccountBaseInfoDO> accountBaseInfoDOList = pageInfo.getList();
    List<SecondaryCollegeAdminQueryRspDTO> secondaryCollegeAdminQueryRspDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(accountBaseInfoDOList)) {
      logger.info(JSON.toJSONString(accountBaseInfoDOList));
      List<Long> userIds = new ArrayList<>();
      for (AccountBaseInfoDO accountBaseInfoDO : accountBaseInfoDOList) {
        SecondaryCollegeAdminQueryRspDTO dto = new SecondaryCollegeAdminQueryRspDTO();
        dto.setUserId(accountBaseInfoDO.getUserId());
        dto.setName(accountBaseInfoDO.getName());
        dto.setCode(accountBaseInfoDO.getCode());
        userIds.add(accountBaseInfoDO.getUserId());
        secondaryCollegeAdminQueryRspDTOList.add(dto);
      }
      List<UserOrgRef> userOrgRefList = userOrgRefService.listByUserIds(userIds);
      Map<Long, List<Long>> userOrgMap = getUserOrgMap(userOrgRefList);
      Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();
      if (!CollectionUtils.isEmpty(secondaryCollegeAdminQueryRspDTOList)) {
        for (SecondaryCollegeAdminQueryRspDTO dto : secondaryCollegeAdminQueryRspDTOList) {
          List<Long> orgIds = userOrgMap.get(dto.getUserId());
          if (orgIds != null) {
            List<CollegeBaseInfoDTO> collegeBaseInfoDTOList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(orgIds)) {
              for (long id : orgIds) {
                CollegeBaseInfoDTO collegeBaseInfoDTO = new CollegeBaseInfoDTO();
                collegeBaseInfoDTO.setCollegeId(id);
                CollegeInfo collegeInfo = collegeInfoMap.get(id);
                if (collegeInfo != null) {
                  collegeBaseInfoDTO.setCollegeName(collegeInfo.getName());
                }
                collegeBaseInfoDTOList.add(collegeBaseInfoDTO);
              }
            }
            dto.setCollegeList(collegeBaseInfoDTOList);
          }
        }
      }
    }

    //3.组装返回结果
    Page<SecondaryCollegeAdminQueryRspDTO> secondaryCollegeAdminQueryRspDTOPage = new Page<>();
    secondaryCollegeAdminQueryRspDTOPage.setResult(secondaryCollegeAdminQueryRspDTOList);
    secondaryCollegeAdminQueryRspDTOPage.setTotalCount((int) pageInfo.getTotal());
    secondaryCollegeAdminQueryRspDTOPage.setPageSize(pageSize);
    secondaryCollegeAdminQueryRspDTOPage.setPageNo(pageNo);
    secondaryCollegeAdminQueryRspDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(secondaryCollegeAdminQueryRspDTOPage);
  }

  @Override
  public PagedResult<DormitoryAdminQueryRspDTO> dormitoryAdminPage(String nameOrCode,
      Integer pageNo, Integer pageSize) {
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    PageInfo<AccountBaseInfoDO> pageInfo = accountService
        .dormitoryAdminPageQuery(nameOrCode, pageNo, pageSize);
    List<AccountBaseInfoDO> accountBaseInfoDOList = pageInfo.getList();
    List<DormitoryAdminQueryRspDTO> dormitoryAdminQueryRspDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(accountBaseInfoDOList)) {
      List<Long> userIds = new ArrayList<>();
      for (AccountBaseInfoDO accountBaseInfoDO : accountBaseInfoDOList) {
        DormitoryAdminQueryRspDTO dto = new DormitoryAdminQueryRspDTO();
        dto.setUserId(accountBaseInfoDO.getUserId());
        dto.setName(accountBaseInfoDO.getName());
        dto.setCode(accountBaseInfoDO.getCode());
        userIds.add(accountBaseInfoDO.getUserId());
        dormitoryAdminQueryRspDTOList.add(dto);
      }
      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserIds(userIds);
      Map<Long, List<Long>> useBuildingMap = getUserBuildingMap(userBuildingRefList);
      Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();
      if (!CollectionUtils.isEmpty(dormitoryAdminQueryRspDTOList)) {
        for (DormitoryAdminQueryRspDTO dto : dormitoryAdminQueryRspDTOList) {
          List<Long> buildingIds = useBuildingMap.get(dto.getUserId());
          if (!CollectionUtils.isEmpty(buildingIds)) {
            List<BuildingBaseInfoDTO> buildingBaseInfoDTOS = new ArrayList<>();
            for (long id : buildingIds) {
              BuildingBaseInfoDTO buildingBaseInfoDTO = new BuildingBaseInfoDTO();
              buildingBaseInfoDTO.setBuildingId(id);
              BuildingInfo buildingInfo = buildingInfoMap.get(id);
              if (buildingInfo != null) {
                buildingBaseInfoDTO.setBuildingName(buildingInfo.getName());
              }
              buildingBaseInfoDTOS.add(buildingBaseInfoDTO);
            }

            dto.setBuildingList(buildingBaseInfoDTOS);
          }
        }
      }
    }

    //3.组装返回结果
    Page<DormitoryAdminQueryRspDTO> dormitoryAdminQueryRspDTOPage = new Page<>();
    dormitoryAdminQueryRspDTOPage.setResult(dormitoryAdminQueryRspDTOList);
    dormitoryAdminQueryRspDTOPage.setTotalCount((int) pageInfo.getTotal());
    dormitoryAdminQueryRspDTOPage.setPageSize(pageSize);
    dormitoryAdminQueryRspDTOPage.setPageNo(pageNo);
    dormitoryAdminQueryRspDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(dormitoryAdminQueryRspDTOPage);
  }

  @Override
  public PagedResult<StudentOfficeAdminQueryRspDTO> studentOfficeAdminPage(String nameOrCode,
      Integer pageNo, Integer pageSize) {
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    PageInfo pageInfo = accountService.studentOfficeAdminPageQuery(nameOrCode, pageNo, pageSize);
    List<AccountBaseInfoDO> accountBaseInfoDOList = pageInfo.getList();
    List<StudentOfficeAdminQueryRspDTO> studentOfficeAdminQueryRspDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(accountBaseInfoDOList)) {
      for (AccountBaseInfoDO accountBaseInfoDO : accountBaseInfoDOList) {
        StudentOfficeAdminQueryRspDTO dto = new StudentOfficeAdminQueryRspDTO();
        dto.setUserId(accountBaseInfoDO.getUserId());
        dto.setName(accountBaseInfoDO.getName());
        dto.setCode(accountBaseInfoDO.getCode());
        studentOfficeAdminQueryRspDTOList.add(dto);
      }
    }

    //3.组装返回结果
    Page<StudentOfficeAdminQueryRspDTO> studentOfficeAdminQueryRspDTOPage = new Page<>();
    studentOfficeAdminQueryRspDTOPage.setResult(studentOfficeAdminQueryRspDTOList);
    studentOfficeAdminQueryRspDTOPage.setTotalCount((int) pageInfo.getTotal());
    studentOfficeAdminQueryRspDTOPage.setPageSize(pageSize);
    studentOfficeAdminQueryRspDTOPage.setPageNo(pageNo);
    studentOfficeAdminQueryRspDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(studentOfficeAdminQueryRspDTOPage);
  }

  @Override
  public Result<List<OrgBaseInfoDTO>> orgTreeQuery() {
    List<CollegeInfo> collegeInfoList = orgCacheService.list();
    List<OrgBaseInfoDTO> orgBaseInfoDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(collegeInfoList)) {
      for (CollegeInfo collegeInfo : collegeInfoList) {
        OrgBaseInfoDTO dto = new OrgBaseInfoDTO();
        dto.setOrgId(collegeInfo.getOrgId());
        dto.setOrgName(collegeInfo.getName());
        dto.setParentOrgId(collegeInfo.getParentOrgId());
        orgBaseInfoDTOList.add(dto);
      }
    }
    return Result.success(orgBaseInfoDTOList);
  }

  @Override
  public PagedResult<StaffBaseInfoDTO> getStaffListByOrg(Long orgId, Integer pageNo,
      Integer pageSize) {

    PageInfo pageInfo = userOrgService.selectByOrgIdForPage(orgId, pageNo, pageSize);
    List<UserOrg> userOrgList = pageInfo.getList();
    List<Long> userIds = getUserIdsFromUserOrgList(userOrgList);
    List<User> userList = userService.selectByPrimaryKeyList(userIds);
    Map<Long, User> userMap = getUserMap(userList);
    List<StaffBaseInfoDTO> staffBaseInfoDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(userIds)) {
      for (UserOrg userOrg : userOrgList) {
        User user = userMap.get(userOrg.getUserId());
        if (user != null) {
          StaffBaseInfoDTO dto = new StaffBaseInfoDTO();
          dto.setUserId(user.getUserId());
          dto.setName(user.getUserName());
          dto.setCode(user.getCode());
          staffBaseInfoDTOList.add(dto);
        }
      }
    }
    //3.组装返回结果
    Page<StaffBaseInfoDTO> staffBaseInfoDTOPage = new Page<>();
    staffBaseInfoDTOPage.setResult(staffBaseInfoDTOList);
    staffBaseInfoDTOPage.setTotalCount((int) pageInfo.getTotal());
    staffBaseInfoDTOPage.setPageSize(pageSize);
    staffBaseInfoDTOPage.setPageNo(pageNo);
    staffBaseInfoDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(staffBaseInfoDTOPage);
  }

  @Override
  public Result<StudentBaseInfoDTO> getStudentBaseInfo(long studentId) {
    StudentBaseInfoDTO dto = new StudentBaseInfoDTO();
    User user = userService.selectByPrimaryKey(studentId);
    if (user == null) {
      logger.warn("学生ID不存在.");
      return Result.success();
    }
    dto.setStudentId(user.getUserId());
    dto.setStudentName(user.getUserName());
    dto.setStudentCode(user.getCode());
    dto.setProfilePhoto(user.getHeadPortraitPath());

    //获取批量UserIds
    List<Long> userIds = new ArrayList<>();
    userIds.add(user.getUserId());

    List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByUserIds(userIds);
    Map<Long, DormitoryUser> userToDormitoryMap = getUserToDormitoryMap(dormitoryUserList);
    Map<Long, DormitoryInfo> dormitoryInfoMap = dormitoryCacheService.getDormitoryMap();
    Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();
    List<UserClass> userClassList = userClassService.listByUserIds(userIds);
    Map<Long, Long> userClassMap = getUserClassMap(userClassList);
    Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();
    Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
    Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();

    dto.setStudentCode(user.getCode());
    DormitoryUser dormitoryUser = userToDormitoryMap.get(user.getUserId());
    setDormitoryAndBuildingInfo(dormitoryInfoMap, buildingInfoMap, dto, dormitoryUser);
    dto.setClassId(userClassMap.get(user.getUserId()));
    ClassInfo classInfo = classInfoMap.get(userClassMap.get(user.getUserId()));
    if (classInfo != null) {
      dto.setClassName(classInfo.getClassCode());
      dto.setInstructorId(classInfo.getInstructorId());
      User instructor = userService.selectByPrimaryKey(classInfo.getInstructorId());
      if (instructor != null) {
        dto.setInstructorName(instructor.getUserName());
      }
      dto.setMajorId(classInfo.getMajorId());
      MajorInfo majorInfo = majorInfoMap.get(classInfo.getMajorId());
      if (majorInfo != null) {
        dto.setMajorName(majorInfo.getName());
        dto.setCollegeId(majorInfo.getOrgId());
        CollegeInfo collegeInfo = collegeInfoMap.get(majorInfo.getOrgId());
        if (collegeInfo != null) {
          dto.setCollegeName(collegeInfo.getName());
        }
      }
    }

    return Result.success(dto);
  }

  @Override
  public Result<List<StaffBaseInfoDTO>> getStaffListByRole(byte roleType) {
    List<Account> accountList = accountService.getByRoleType(roleType);
    if (CollectionUtils.isEmpty(accountList)) {
      return Result.success(EMPTY_LIST);
    }
    List<Long> userIds = ConvertUtil.getUserIds(accountList);
    List<User> userList = userService.selectByPrimaryKeyList(userIds);
    List<StaffBaseInfoDTO> staffBaseInfoDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(userList)) {
      staffBaseInfoDTOList = userList.stream().map(e -> {
        StaffBaseInfoDTO dto = new StaffBaseInfoDTO();
        dto.setUserId(e.getUserId());
        dto.setName(e.getUserName());
        dto.setCode(e.getCode());
        return dto;
      }).collect(Collectors.toList());
    }
    return Result.success(staffBaseInfoDTOList);
  }

  @Override
  public Result deleteAccount(DeleteAccountReqDTO reqDTO) {
    if (!CollectionUtils.isEmpty(reqDTO.getUserIds())) {
      accountService.delete(reqDTO.getRoleType(), reqDTO.getUserIds());
      if (RoleType.SecondaryCollegeAdmin.getType() == reqDTO.getRoleType()) {
        userOrgRefService.deleteByUserIds(reqDTO.getUserIds());
      } else if (RoleType.DormitoryAdmin.getType() == reqDTO.getRoleType()) {
        userBuildingService.deleteByUserIds(reqDTO.getUserIds());
      }
    }
    return Result.success();
  }

  @Override
  public Result studentOfficeAdminSave(StudentOfficeAdminSaveReqDTO reqDTO) {
    List<Long> staffIdList = reqDTO.getStaffIdList();
    checkAccount(staffIdList);
    if (!CollectionUtils.isEmpty(staffIdList)) {
      saveAccount(staffIdList, RoleType.StudentsAffairsAdmin.getType());
    }
    return Result.success();
  }

  @Override
  public Result dormitoryAdminSave(DormitoryAdminSaveReqDTO reqDTO) {
    List<DormitoryAdminRelationDTO> dormitoryAdminRelationDTOList = reqDTO.getRefList();
    if (!CollectionUtils.isEmpty(dormitoryAdminRelationDTOList)) {
      List<Long> userIds = getUserIdsByDAR(dormitoryAdminRelationDTOList);
      checkAccount(userIds);
      List<UserBuildingRef> userBuildingRefList = getUserBuildingRefList(
          dormitoryAdminRelationDTOList);
      //保存帐号
      saveAccount(userIds, RoleType.DormitoryAdmin.getType());

      userBuildingService.batchInsert(userIds, userBuildingRefList);
    }
    return Result.success();
  }

  private void checkAccount(List<Long> staffIdList) {
    List<Long> instructorIds = classCacheService.getInstructorIds();
    if (CollectionUtils.isEmpty(staffIdList)) {
      return;
    }
    if (CollectionUtils.isEmpty(instructorIds)) {
      return;
    }
    for (Long staffId : staffIdList) {
      if (instructorIds.contains(staffId)) {
        logger.warn("用户:{}是辅导员.", staffId);
        throw new BusinessException(ErrorCode.INSTURCTOR_CANNOT_ADD);
      }
    }
  }

  private void saveAccount(List<Long> userIds, byte roleType) {
    List<Account> accountList = new ArrayList<>();
    for (Long uid : userIds) {
      Account currAccount = accountService.getAccountByUserId(uid);
      if (currAccount != null) {
        if (roleType != currAccount.getRoleType().byteValue()) {
          logger.warn("一个用户只能有一个角色.userId:{} , roleType:{}", uid, currAccount.getRoleType());
          throw new BusinessException(ErrorCode.USER_HAS_ONLY_ONE_ROLE);
        }
      } else {
        Account account = new Account();
        account.setId(DateUtil.uuid());
        account.setRoleType(roleType);
        account.setUserId(uid);
        account.setPassword(PasswordUtil.hashPwd(ConfigConstants.DEFAULT_PASSWORD));
        accountList.add(account);
      }
    }
    accountService.batchInsert(accountList);
  }

  @Override
  public Result secondaryCollegeAdminSave(SecondaryCollegeAdminSaveReqDTO reqDTO) {
    if (!CollectionUtils.isEmpty(reqDTO.getRefList())) {
      List<Long> userIds = getUserIdsBySCA(reqDTO.getRefList());
      checkAccount(userIds);
      List<UserOrgRef> userOrgRefList = getUserOrgRefList(reqDTO.getRefList());
      //保存帐号
      saveAccount(userIds, RoleType.SecondaryCollegeAdmin.getType());
      userOrgRefService.batchInsert(userIds, userOrgRefList);
    }
    return Result.success();
  }


  public List<Long> getUserIdsBySCA(List<SecondaryCollegeAdminRelationDTO> relationDTOList) {
    return relationDTOList.stream().map(e -> e.getUserId()).collect(Collectors.toList());
  }

  private List<UserOrgRef> getUserOrgRefList(
      List<SecondaryCollegeAdminRelationDTO> relationDTOList) {
    List<UserOrgRef> userOrgRefList = new ArrayList<>();
    for (SecondaryCollegeAdminRelationDTO dto : relationDTOList) {
      List<Long> orgIds = dto.getOrgIdList();
      if (!CollectionUtils.isEmpty(orgIds)) {
        for (Long bid : orgIds) {
          UserOrgRef ref = new UserOrgRef();
          ref.setUserId(dto.getUserId());
          ref.setOrgId(bid);
          userOrgRefList.add(ref);
        }
      }
    }
    return userOrgRefList;
  }

  private List<UserBuildingRef> getUserBuildingRefList(
      List<DormitoryAdminRelationDTO> dormitoryAdminRelationDTOList) {
    List<UserBuildingRef> userBuildingRefList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(dormitoryAdminRelationDTOList)) {
      for (DormitoryAdminRelationDTO dto : dormitoryAdminRelationDTOList) {
        List<Long> buildingIds = dto.getBuildingId();
        if (!CollectionUtils.isEmpty(buildingIds)) {
          for (Long bid : buildingIds) {
            UserBuildingRef ref = new UserBuildingRef();
            ref.setUserId(dto.getUserId());
            ref.setBuildingId(bid);
            userBuildingRefList.add(ref);
          }
        }
      }
    }
    return userBuildingRefList;
  }

  private List<Long> getUserIdsByDAR(
      List<DormitoryAdminRelationDTO> dormitoryAdminRelationDTOList) {
    if (!CollectionUtils.isEmpty(dormitoryAdminRelationDTOList)) {
      return dormitoryAdminRelationDTOList.stream().map(e -> e.getUserId())
          .collect(Collectors.toList());
    }
    return EMPTY_LIST;
  }

  private Map<Long, User> getUserMap(List<User> userList) {
    if (!CollectionUtils.isEmpty(userList)) {
      return userList.stream()
          .collect(Collectors.toMap(User::getUserId, Function.identity(), (k, v) -> v));
    }
    return EMPTY_MAP;
  }

  private List<Long> getUserIdsFromUserOrgList(List<UserOrg> userOrgList) {
    if (!CollectionUtils.isEmpty(userOrgList)) {
      return userOrgList.stream().map(e -> e.getUserId()).collect(Collectors.toList());
    }
    return EMPTY_LIST;
  }

  private Map<Long, List<Long>> getUserBuildingMap(List<UserBuildingRef> userOrgRefList) {
    if (!CollectionUtils.isEmpty(userOrgRefList)) {
      return userOrgRefList.stream().collect(groupingBy(UserBuildingRef::getUserId,
          Collectors.mapping(UserBuildingRef::getBuildingId, Collectors.toList())));
    }
    return EMPTY_MAP;
  }

  private Map<Long, List<Long>> getUserOrgMap(List<UserOrgRef> userOrgRefList) {
    if (!CollectionUtils.isEmpty(userOrgRefList)) {
      return userOrgRefList.stream().collect(groupingBy(UserOrgRef::getUserId,
          Collectors.mapping(UserOrgRef::getOrgId, Collectors.toList())));
    }
    return EMPTY_MAP;
  }

  private Map<Long, User> getInstructorMap(List<User> instructorList) {
    if (!CollectionUtils.isEmpty(instructorList)) {
      return instructorList.stream()
          .collect(Collectors.toMap(User::getUserId, Function.identity(), (k, v) -> v));
    }
    return EMPTY_MAP;
  }

  private Map<Long, Long> getUserClassMap(List<UserClass> userClassList) {
    if (!CollectionUtils.isEmpty(userClassList)) {
      return userClassList.stream()
          .collect(Collectors.toMap(UserClass::getUserId, UserClass::getClassId, (k, v) -> v));
    }
    return EMPTY_MAP;
  }

  private Map<Long, DormitoryUser> getUserToDormitoryMap(List<DormitoryUser> dormitoryUserList) {
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      return dormitoryUserList.stream()
          .collect(Collectors.toMap(DormitoryUser::getUserId, Function.identity(), (k, v) -> v));
    }
    return EMPTY_MAP;
  }

  private List<Long> getUserIds(List<User> userList) {
    return userList.stream().map(e -> e.getUserId()).collect(Collectors.toList());
  }
}
