package com.yunhuakeji.attendance.biz.impl;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.biz.CommonHandlerUtil;
import com.yunhuakeji.attendance.biz.CommonQueryUtil;
import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.DataRecheckBiz;
import com.yunhuakeji.attendance.cache.*;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.dao.basedao.model.*;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentCareCountStatDO;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentStatusCountDO;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.dto.response.StudentClockCareStatRspDTO;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.service.baseservice.ClassInfoService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.CareService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.UserOrgRefService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataRecheckBizImpl implements DataRecheckBiz {

  @Autowired
  private UserService userService;

  @Autowired
  private MajorCacheService majorCacheService;

  @Autowired
  private ClassInfoService classInfoService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private OrgCacheService orgCacheService;

  @Autowired
  private BuildingCacheService buildingCacheService;

  @Autowired
  private DormitoryCacheService dormitoryCacheService;

  @Autowired
  private StudentClockService studentClockService;

  @Autowired
  private CareService careService;

  @Autowired
  private UserOrgRefService userOrgRefService;


  private List<Long> getOrgIds(Long userId) {
    List<UserOrgRef> userOrgRefList = userOrgRefService.listByUserId(userId);
    if (CollectionUtils.isEmpty(userOrgRefList)) {
      return Collections.EMPTY_LIST;
    }
    return userOrgRefList.stream().map(e -> e.getOrgId()).collect(Collectors.toList());
  }

  private List<Long> getOrgIds(Long orgId, Long userId) {
    List<Long> orgIds = new ArrayList<>();
    if (userId != null) {
      List<Long> orgIdList = getOrgIds(userId);
      if (!CollectionUtils.isEmpty(orgIdList)) {
        orgIds.addAll(orgIdList);
      } else {
        return Collections.EMPTY_LIST;
      }
    }
    if (orgId != null) {
      if (!CollectionUtils.isEmpty(orgIds)) {
        if (orgIds.contains(orgId)) {
          orgIds.clear();
          orgIds.add(orgId);
        } else {
          return Collections.EMPTY_LIST;
        }
      } else {
        orgIds.add(orgId);
      }
    }
    return orgIds;
  }

  @Override
  public PagedResult<StudentClockCareStatRspDTO> studentClockStatQueryPage(
      Long orgId, Long majorId, Long instructorId, Long buildingId, String nameOrCode, Integer pageNo, Integer pageSize
      , Long userId) {
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    PageInfo<StudentKeysInfo> pageInfo;
    List<StudentClockCareStatRspDTO> studentClockCareStatRspDTOList = new ArrayList<>();
    Page<StudentClockCareStatRspDTO> page = new Page<>();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    page.setResult(studentClockCareStatRspDTOList);


    List<Long> instructorIds = new ArrayList<>();
    if (StringUtils.isNotBlank(nameOrCode)) {
      pageInfo = userService.getStudentForPageByNameOrCode(nameOrCode, pageNo, pageSize);
      page.setTotalCount((int) pageInfo.getTotal());
    } else {
      List<Long> orgIds = null;
      if (orgId != null || userId != null) {
        orgIds = getOrgIds(orgId, userId);
        if (CollectionUtils.isEmpty(orgIds)) {
          return PagedResult.success(pageNo, pageSize);
        }
      }
      List<Long> orgClassIds = CommonQueryUtil.getClassIdsByOrgIds(orgIds);
      List<Long> majorClassIds = CommonQueryUtil.getClassIdsByMajorId(majorId);
      List<Long> instructorClassIds = CommonQueryUtil.getClassIdsByInstructorId(instructorId);
      List<Long> lastClassIds = ConvertUtil.getLastClassIds(orgClassIds, majorClassIds, instructorClassIds);

      //根据classId和状态查询学生昨天的状态
      if (orgId != null || majorId != null || instructorId != null || majorId != null) {
        if (CollectionUtils.isEmpty(lastClassIds)) {
          return PagedResult.success(pageNo, pageSize);
        }
      }
      pageInfo = userService.getStudentForPageByClassIdsAndBuildingId(lastClassIds, buildingId, pageNo, pageSize);
      page.setTotalCount((int) pageInfo.getTotal());
    }

    if (!CollectionUtils.isEmpty(pageInfo.getList())) {
      Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();
      Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
      Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();
      Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();
      Map<Long, DormitoryInfo> dormitoryInfoMap = dormitoryCacheService.getDormitoryMap();
      for (StudentKeysInfo studentKeysInfo : pageInfo.getList()) {
        StudentClockCareStatRspDTO dto = new StudentClockCareStatRspDTO();
        dto.setBedCode(studentKeysInfo.getBedCode());
        dto.setClassId(studentKeysInfo.getClassId());
        ClassInfo classInfo = classInfoMap.get(studentKeysInfo.getClassId());
        if (classInfo != null) {
          dto.setClassName(classInfo.getClassCode());
          dto.setMajorId(classInfo.getMajorId());
          dto.setInstructorId(classInfo.getInstructorId());
          instructorIds.add(classInfo.getInstructorId());
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

        dto.setDormitoryId(studentKeysInfo.getDormitoryId());
        DormitoryInfo dormitoryInfo = dormitoryInfoMap.get(studentKeysInfo.getDormitoryId());
        if (dormitoryInfo != null) {
          dto.setDormitoryName(dormitoryInfo.getName());
          BuildingInfo buildingInfo = buildingInfoMap.get(dormitoryInfo.getBuildingId());
          dto.setBuildingId(dormitoryInfo.getBuildingId());
          if (buildingInfo != null) {
            dto.setBuildingName(buildingInfo.getName());
          }
        }
        dto.setProfilePhoto(studentKeysInfo.getHeadPortraitPath());
        dto.setStudentCode(studentKeysInfo.getCode());
        dto.setStudentName(studentKeysInfo.getName());
        dto.setStudentId(studentKeysInfo.getUserId());
        studentClockCareStatRspDTOList.add(dto);
      }
    }

    if (!CollectionUtils.isEmpty(instructorIds) && !CollectionUtils.isEmpty(studentClockCareStatRspDTOList)) {
      List<User> instructorList = userService.selectByPrimaryKeyList(instructorIds);
      List<Long> studentIds = getStudentIdsByStudentClockCareStat(studentClockCareStatRspDTOList);
      Date endClockDate = DateUtil.add(new Date(), Calendar.DAY_OF_YEAR, -1);
      endClockDate = DateUtil.getDateEndTime(endClockDate);
      List<StudentStatusCountDO> studentStatusCountDOList =
          studentClockService.studentStatusCountStatByStudentIds(studentIds, null, endClockDate);
      Map<Long, List<StudentStatusCountDO>> map = ConvertUtil.getStudentStatusCountMap(studentStatusCountDOList);

      List<StudentCareCountStatDO> studentCareCountStatDOS = careService.studentCareCountStat(studentIds);
      Map<Long, Integer> studentCareCountMap = ConvertUtil.getStudentCareCountMap(studentCareCountStatDOS);

      Map<Long, User> instructorMap = getInstructorMap(instructorList);
      for (StudentClockCareStatRspDTO dto : studentClockCareStatRspDTOList) {
        User user = instructorMap.get(dto.getInstructorId());
        dto.setInstructorName(user.getUserName());

        List<StudentStatusCountDO> studentStatusCountDOS = map.get(dto.getStudentId());
        if (!CollectionUtils.isEmpty(studentStatusCountDOS)) {
          for (StudentStatusCountDO s : studentStatusCountDOS) {
            if (s.getClockStatus() != null && ClockStatus.STAYOUT.getType() == s.getClockStatus()) {
              dto.setTotalStayOut(s.getStatCount());
            } else if (s.getClockStatus() != null && ClockStatus.STAYOUT_LATE.getType() == s.getClockStatus()) {
              dto.setTotalStayOutLate(s.getStatCount());
            }
          }
        }
        Integer count = studentCareCountMap.get(dto.getStudentId());
        dto.setTotalCared(count != null ? count : 0);
      }
    }

    return PagedResult.success(page);
  }


  private List<Long> getStudentIdsByStudentClockCareStat(List<StudentClockCareStatRspDTO> studentClockCareStatRspDTOList) {
    List<Long> studentIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(studentClockCareStatRspDTOList)) {
      for (StudentClockCareStatRspDTO s : studentClockCareStatRspDTOList) {
        studentIds.add(s.getStudentId());
      }
    }
    return studentIds;
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

  private List<Long> getClassIds(List<ClassInfo> classInfoList) {
    List<Long> classIds = new ArrayList<>();
    for (ClassInfo classInfo : classInfoList) {
      classIds.add(classInfo.getClassId());
    }
    return classIds;
  }
}
