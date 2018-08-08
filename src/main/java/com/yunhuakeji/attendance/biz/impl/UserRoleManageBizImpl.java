package com.yunhuakeji.attendance.biz.impl;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.biz.UserRoleManageBiz;
import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.DormitoryCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryUser;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentDeviceRef;
import com.yunhuakeji.attendance.dto.request.ClearFrequentlyUsedPhoneReqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorManageQueryDTO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.service.baseservice.DormitoryUserService;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.StudentDeviceRefService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleManageBizImpl implements UserRoleManageBiz {

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

  @Override
  public PagedResult<StudentBaseInfoDTO> studentPageQuery(String name, String code, Integer pageNo, Integer pageSize) {

    PageInfo pageInfo = userService.getStudentForPage(name, code, pageNo, pageSize);
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
      //dormitoryInfoService.
      for (User user : userList) {
        StudentBaseInfoDTO dto = new StudentBaseInfoDTO();
        dto.setStudentCode(user.getCode());
        DormitoryUser dormitoryUser = userToDormitoryMap.get(user.getUserId());
        if (dormitoryUser != null) {
          dto.setDormitoryId(dormitoryUser.getDormitoryId());
          dto.setBedCode(dormitoryUser.getBedCode());
          DormitoryInfo dormitoryInfo = dormitoryInfoMap.get(dormitoryUser.getDormitoryId());
          if (dormitoryInfo != null) {
            dto.setDormitoryName(dormitoryInfo.getName());
            dto.setBuildingId(dormitoryInfo.getBuildingId());
            BuildingInfo buildingInfo = buildingInfoMap.get(dormitoryInfo.getBuildingId());
            if (buildingInfo != null) {
              dto.setBuildingName(buildingInfo.getName());
            }
          }
        }
        dto.setClassId(userClassMap.get(user.getUserId()));
        ClassInfo classInfo = classInfoMap.get(userClassMap.get(user.getUserId()));
        if (classInfo != null) {
          dto.setClassName(classInfo.getClassCode());
          dto.setInstructorId(classInfo.getInstructorId());
          instructorIds.add(classInfo.getInstructorId());
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
        dto.setProfilePhoto(user.getHeadPortraitPath());
        dto.setStudentId(user.getUserId());
        dto.setStudentName(user.getUserName());
        studentBaseInfoDTOList.add(dto);
      }
    }

    if (!CollectionUtils.isEmpty(instructorIds) && !CollectionUtils.isEmpty(studentBaseInfoDTOList)) {
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

  @Override
  public Result clearFrequentlyUsedPhone(ClearFrequentlyUsedPhoneReqDTO reqDTO) {
    studentDeviceRefService.deleteByStudentIds(reqDTO.getStudentIds());
    return Result.success(null);
  }

  @Override
  public PagedResult<InstructorManageQueryDTO> instructorPageQuery(String name, String code, Integer pageNo, Integer pageSize) {
    return null;
  }

  private Map<Long, User> getInstructorMap(List<User> instructorList) {
    Map<Long, User> instructorMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(instructorList)) {
      for (User user : instructorList) {
        instructorMap.put(user.getUserId(), user);
      }
    }
    return instructorMap;
  }

  private Map<Long, Long> getUserClassMap(List<UserClass> userClassList) {
    Map<Long, Long> userClassMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(userClassList)) {
      for (UserClass userClass : userClassList) {
        userClassMap.put(userClass.getUserId(), userClass.getClassId());
      }
    }
    return userClassMap;
  }

  private Map<Long, DormitoryUser> getUserToDormitoryMap(List<DormitoryUser> dormitoryUserList) {
    Map<Long, DormitoryUser> userToDormitoryMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      for (DormitoryUser dormitoryUser : dormitoryUserList) {
        userToDormitoryMap.put(dormitoryUser.getUserId(), dormitoryUser);
      }
    }
    return userToDormitoryMap;
  }

  private List<Long> getUserIds(List<User> userList) {
    List<Long> userIds = new ArrayList<>();
    for (User user : userList) {
      userIds.add(user.getUserId());
    }
    return userIds;
  }
}
