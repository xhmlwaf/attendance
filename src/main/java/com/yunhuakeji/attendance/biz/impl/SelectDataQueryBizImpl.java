package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.CommonQueryUtil;
import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.SelectDataQueryBiz;
import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dao.bizdao.model.TermConfig;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.CollegeBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.InstructorQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.MajorQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.UserBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.WeekInfoRspDTO;
import com.yunhuakeji.attendance.enums.OrgType;
import com.yunhuakeji.attendance.enums.RoleType;
import com.yunhuakeji.attendance.service.baseservice.ClassInfoService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.AccountService;
import com.yunhuakeji.attendance.service.bizservice.TermConfigService;
import com.yunhuakeji.attendance.service.bizservice.UserOrgRefService;
import com.yunhuakeji.attendance.util.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class SelectDataQueryBizImpl implements SelectDataQueryBiz {

  private static final Logger logger = LoggerFactory.getLogger(SelectDataQueryBizImpl.class);

  @Autowired
  private OrgCacheService orgCacheService;

  @Autowired
  private MajorCacheService majorCacheService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private ClassInfoService classInfoService;

  @Autowired
  private UserService userService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private UserOrgRefService userOrgRefService;

  @Autowired
  private TermConfigService termConfigService;

  @Autowired
  private BuildingCacheService buildingCacheService;

  private List<Long> getOrgIds(Long userId) {
    List<UserOrgRef> userOrgRefList = userOrgRefService.listByUserId(userId);
    if (CollectionUtils.isEmpty(userOrgRefList)) {
      List<CollegeInfo> collegeInfoList = orgCacheService.list();
      return collegeInfoList.stream().map(e -> e.getOrgId()).collect(Collectors.toList());
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
  public Result<List<BuildingQueryRspDTO>> listAllBuilding() {
    List<BuildingInfo> buildingInfoList = buildingCacheService.list();
    List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(buildingInfoList)) {
      for (BuildingInfo buildingInfo : buildingInfoList) {
        rspDTOList.add(convertToDormitoryBuildingQueryRspDTO(buildingInfo));
      }
    }
    return Result.success(rspDTOList);
  }

  @Override
  public Result<List<CollegeBaseInfoDTO>> listAllSecondaryCollege() {
    List<CollegeInfo> collegeInfoList = orgCacheService.list();
    List<CollegeBaseInfoDTO> collegeBaseInfoDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(collegeInfoList)) {
      for (CollegeInfo collegeInfo : collegeInfoList) {
        if (collegeInfo.getType() == OrgType.JXL.getType()) {
          CollegeBaseInfoDTO dto = new CollegeBaseInfoDTO();
          dto.setCollegeId(collegeInfo.getOrgId());
          dto.setCollegeName(collegeInfo.getName());
          collegeBaseInfoDTOList.add(dto);
        }
      }
    }
    return Result.success(collegeBaseInfoDTOList);
  }

  @Override
  public Result<List<WeekInfoRspDTO>> listAllWeekInfo() {
    TermConfig termConfig = termConfigService.getLastTermConfig();
    if (termConfig == null) {
      logger.warn("不在学期内");
      return Result.success(new ArrayList<>());
    }
    Date startDate = termConfig.getStartDate();
    Date endDate = termConfig.getEndDate();
    logger.info("startDate:{}", DateUtil.dateToStr(startDate, DateUtil.DATESTYLE_YYYY_MM_DD));
    logger.info("endDate:{}", DateUtil.dateToStr(endDate, DateUtil.DATESTYLE_YYYY_MM_DD));
    return Result.success(ConvertUtil.getByStartEndDate(startDate, endDate));
  }

  @Override
  public Result<List<MajorQueryRspDTO>> listAllMajorInfo(Long orgId) {

    List<MajorInfo> majorInfoList = majorCacheService.list();
    List<MajorQueryRspDTO> majorQueryRspDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(majorInfoList)) {
      for (MajorInfo majorInfo : majorInfoList) {
        if (orgId != null) {
          if (majorInfo.getOrgId().equals(orgId)) {
            majorQueryRspDTOList.add(getByMajorInfo(majorInfo));
          }
        } else {
          majorQueryRspDTOList.add(getByMajorInfo(majorInfo));
        }
      }
    }

    return Result.success(majorQueryRspDTOList);
  }

  private MajorQueryRspDTO getByMajorInfo(MajorInfo majorInfo) {
    MajorQueryRspDTO dto = new MajorQueryRspDTO();
    dto.setMajorId(majorInfo.getMajorId());
    dto.setMajorName(majorInfo.getName());
    return dto;
  }

  @Override
  public Result<List<InstructorQueryRspDTO>> listInstructor(Long orgId, Long majorId, Long userId) {
    List<Long> orgIds = null;
    if (orgId != null || userId != null) {
      orgIds = getOrgIds(orgId, userId);
      if (CollectionUtils.isEmpty(orgIds)) {
        return Result.success(null);
      }
    }

    List<Long> orgClassIds = CommonQueryUtil.getClassIdsByOrgIds(orgIds);
    List<Long> majorClassIds = CommonQueryUtil.getClassIdsByMajorId(majorId);
    List<Long> lastClassIds = ConvertUtil
        .getLastClassIds(orgClassIds, majorClassIds, null);
    if (orgId != null || userId != null || majorId != null) {
      if (CollectionUtils.isEmpty(lastClassIds)) {
        return Result.success(null);
      }
    }
    List<Long> instructorIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(lastClassIds)) {
      List<ClassInfo> classInfos = classInfoService.selectByPrimaryKeyList(lastClassIds);
      instructorIds = ConvertUtil.getInstructorIds(classInfos);
    }
    List<InstructorQueryRspDTO> instructorQueryRspDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(instructorIds)) {
      List<User> instructorList = userService.selectByPrimaryKeyList(instructorIds);
      if (!CollectionUtils.isEmpty(instructorList)) {
        for (User user : instructorList) {
          InstructorQueryRspDTO dto = new InstructorQueryRspDTO();
          dto.setName(user.getUserName());
          dto.setUserId(user.getUserId());
          instructorQueryRspDTOList.add(dto);
        }
      }
    }

    return Result.success(instructorQueryRspDTOList);
  }

  @Override
  public Result<List<CollegeBaseInfoDTO>> listByUserId(Long userId) {
    Account account = accountService.getAccountByUserId(userId);
    if (account == null) {
      logger.warn("该用户没有角色权限.userId:{}", userId);
      return Result.success(new ArrayList<>());
    }
    if (RoleType.StudentsAffairsAdmin.getType() == account.getRoleType().byteValue()) {
      List<CollegeInfo> collegeInfoList = orgCacheService.list();
      return Result.success(ConvertUtil.getCollegeBaseInfoDTO(collegeInfoList));
    } else if (RoleType.SecondaryCollegeAdmin.getType() == account.getRoleType().byteValue()) {
      List<UserOrgRef> userOrgRefList = userOrgRefService.listByUserId(userId);
      List<Long> orgIds = ConvertUtil.getOrgIds(userOrgRefList);
      List<CollegeInfo> collegeInfoList = new ArrayList<>();
      if (!CollectionUtils.isEmpty(orgIds)) {
        Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();
        for (Long orgId : orgIds) {
          CollegeInfo collegeInfo = collegeInfoMap.get(orgId);
          if (collegeInfo != null) {
            collegeInfoList.add(collegeInfo);
          }
        }
        return Result.success(ConvertUtil.getCollegeBaseInfoDTO(collegeInfoList));
      }
    }
    return Result.success(new ArrayList<>());
  }

  @Override
  public Result<UserBaseInfoDTO> getUserBasicInfo(Long userId) {
    User user = userService.selectByPrimaryKey(userId);
    if (user == null) {
      return Result.success();
    }
    UserBaseInfoDTO userBaseInfoDTO = new UserBaseInfoDTO();
    userBaseInfoDTO.setUserId(user.getUserId());
    userBaseInfoDTO.setName(user.getUserName());
    userBaseInfoDTO.setCode(user.getCode());
    userBaseInfoDTO.setProfilePhoto(user.getHeadPortraitPath());
    return Result.success(userBaseInfoDTO);
  }

  @Override
  public Result<Integer> getCurrWeekNum() {
    TermConfig termConfig = termConfigService.getLastTermConfig();
    if (termConfig == null) {
      logger.warn("不在学期内");
      return Result.success(null);
    }
    Date startDate = termConfig.getStartDate();
    Date endDate = termConfig.getEndDate();

    List<WeekInfoRspDTO> weekInfoRspDTOS = ConvertUtil.getByStartEndDate(startDate, endDate);
    if (!CollectionUtils.isEmpty(weekInfoRspDTOS)) {
      long currDate = DateUtil
          .getYearMonthDayByDate(DateUtil.add(new Date(), Calendar.DAY_OF_YEAR, 1));
      for (WeekInfoRspDTO dto : weekInfoRspDTOS) {
        if (currDate >= DateUtil.getYearMonthDayByDate(dto.getStartDate()) && currDate <= DateUtil
            .getYearMonthDayByDate(dto.getEndDate())) {
          return Result.success(dto.getWeekNumber());
        }
      }
    }
    return Result.success(null);
  }


  private BuildingQueryRspDTO convertToDormitoryBuildingQueryRspDTO(BuildingInfo buildingInfo) {
    BuildingQueryRspDTO dto = new BuildingQueryRspDTO();
    dto.setBuildingId(buildingInfo.getBuildingId());
    dto.setBuildingName(buildingInfo.getName());
    dto.setFloorNumber(buildingInfo.getTotalFloor());
    return dto;
  }
}
