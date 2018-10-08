package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.CommonHandlerUtil;
import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.DormitoryBiz;
import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.comparator.BuildingQueryRspDTOCompatator01;
import com.yunhuakeji.attendance.comparator.DormitoryClockStatCompatator01;
import com.yunhuakeji.attendance.comparator.DormitoryClockStatCompatator02;
import com.yunhuakeji.attendance.comparator.DormitoryClockStatCompatator03;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryUser;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dao.bizdao.model.CheckDormitory;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusDO;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockStatusCountStatDO;
import com.yunhuakeji.attendance.dao.bizdao.model.TermConfig;
import com.yunhuakeji.attendance.dao.bizdao.model.UserBuildingRef;
import com.yunhuakeji.attendance.dao.bizdao.model.UserClockCountStatDO;
import com.yunhuakeji.attendance.dto.request.DormitoryCheckOverReqDTO;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckDayStatListRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckDayStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckWeekStatListRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckWeekStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitorySimpleRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryStudentStatRspDTO;
import com.yunhuakeji.attendance.dto.response.StudentDormitoryRsqDTO;
import com.yunhuakeji.attendance.dto.response.WeekInfoRspDTO;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.enums.RoleType;
import com.yunhuakeji.attendance.service.baseservice.DormitoryInfoService;
import com.yunhuakeji.attendance.service.baseservice.DormitoryUserService;
import com.yunhuakeji.attendance.service.baseservice.StudentInfoService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.AccountService;
import com.yunhuakeji.attendance.service.bizservice.CheckDormitoryService;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.service.bizservice.ClockSettingService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.TermConfigService;
import com.yunhuakeji.attendance.service.bizservice.UserBuildingService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DormitoryBizImpl implements DormitoryBiz {

  private static final Logger logger = LoggerFactory.getLogger(DormitoryBizImpl.class);


  @Autowired
  private DormitoryInfoService dormitoryInfoService;

  @Autowired
  private UserBuildingService userBuildingService;

  @Autowired
  private BuildingCacheService buildingCacheService;

  @Autowired
  private DormitoryUserService dormitoryUserService;

  @Autowired
  private StudentClockService studentClockService;

  @Autowired
  private CheckDormitoryService checkDormitoryService;

  @Autowired
  private StudentInfoService studentInfoService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private UserService userService;

  @Autowired
  private TermConfigService termConfigService;

  @Autowired
  private ClockDaySettingService clockDaySettingService;

  @Autowired
  private ClockSettingService clockSettingService;


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
  public Result<List<DormitorySimpleRspDTO>> listDormitory(Long buildingId, Integer floorNumber) {

    List<DormitorySimpleRspDTO> resultList = new ArrayList<>();
    List<DormitoryInfo> dormitoryInfoList = dormitoryInfoService.list(buildingId, floorNumber);
    if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
      for (DormitoryInfo dormitoryInfo : dormitoryInfoList) {
        resultList.add(convertToDormitoryQueryRspDTO(dormitoryInfo));
      }
    }
    return Result.success(resultList);
  }

  private byte getRoleTypeByUserId(Long userId) {
    Account account = accountService.getAccountByUserId(userId);
    if (account == null) {
      return -1;
    }
    return account.getRoleType();
  }


  @Override
  public Result<List<BuildingQueryRspDTO>> listBuildingForApp(Long userId) {

    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();

    if (instructorIds != null && instructorIds.contains(userId)) {
      List<DormitoryInfo> dormitoryInfoList = dormitoryInfoService.listDormitoryByInstructorId(userId);
      Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();
      List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
      if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
        Set<Long> buildingIds = ConvertUtil.getBuildingIdsByDormitoryInfo(dormitoryInfoList);
        if (!CollectionUtils.isEmpty(buildingIds)) {
          for (Long buildingId : buildingIds) {
            BuildingQueryRspDTO dto = new BuildingQueryRspDTO();
            BuildingInfo buildingInfo = buildingInfoMap.get(buildingId);
            if (buildingInfo != null) {
              dto.setBuildingId(buildingInfo.getBuildingId());
              dto.setBuildingName(buildingInfo.getName());
              dto.setFloorNumber(buildingInfo.getTotalFloor());
              rspDTOList.add(dto);
            }
          }
        }
      }
      return Result.success(rspDTOList);
    } else if (RoleType.DormitoryAdmin.getType() == roleType) {
      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
      Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();
      List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
      if (!CollectionUtils.isEmpty(userBuildingRefList)) {
        for (UserBuildingRef userBuildingRef : userBuildingRefList) {
          BuildingQueryRspDTO dto = new BuildingQueryRspDTO();
          dto.setBuildingId(userBuildingRef.getBuildingId());
          if (buildingInfoMap.get(userBuildingRef.getBuildingId()) != null) {
            dto.setBuildingName(buildingInfoMap.get(userBuildingRef.getBuildingId()).getName());
            dto.setFloorNumber(buildingInfoMap.get(userBuildingRef.getBuildingId()).getTotalFloor());
            rspDTOList.add(dto);
          }
        }
      }
      return Result.success(rspDTOList);

    } else if (RoleType.StudentsAffairsAdmin.getType() == roleType) {
      List<BuildingInfo> buildingInfoList = buildingCacheService.list();
      List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
      if (!CollectionUtils.isEmpty(buildingInfoList)) {
        for (BuildingInfo buildingInfo : buildingInfoList) {
          rspDTOList.add(convertToDormitoryBuildingQueryRspDTO(buildingInfo));
        }
      }
      rspDTOList.sort(new BuildingQueryRspDTOCompatator01());
      return Result.success(rspDTOList);
    }
    return Result.success(new ArrayList<>());
  }

  @Override
  public Result<List<DormitorySimpleRspDTO>> listDormitoryForApp(Long userId, Long buildingId, Integer floorNumber) {
    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();
    if (instructorIds != null && instructorIds.contains(userId)) {
      List<DormitoryInfo> dormitoryInfoList = dormitoryInfoService.listDormitoryByInstructorId(userId);
      List<DormitorySimpleRspDTO> rspDTOList = new ArrayList<>();
      if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
        for (DormitoryInfo dormitoryInfo : dormitoryInfoList) {
          if (dormitoryInfo.getBuildingId().equals(buildingId) && (dormitoryInfo.getFloorNumber() == null
              || dormitoryInfo.getFloorNumber().intValue() == floorNumber)) {
            DormitorySimpleRspDTO dto = new DormitorySimpleRspDTO();
            dto.setDormitoryId(dormitoryInfo.getDormitoryId());
            dto.setDormitoryName(dormitoryInfo.getName());
            rspDTOList.add(dto);
          }
        }
      }
      return Result.success(rspDTOList);
    } else if (RoleType.DormitoryAdmin.getType() == roleType || RoleType.StudentsAffairsAdmin.getType() == roleType) {
      List<DormitoryInfo> dormitoryInfoList = dormitoryInfoService.list(buildingId, floorNumber);
      List<DormitorySimpleRspDTO> rspDTOList = new ArrayList<>();
      if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
        for (DormitoryInfo dormitoryInfo : dormitoryInfoList) {
          DormitorySimpleRspDTO dto = new DormitorySimpleRspDTO();
          dto.setDormitoryId(dormitoryInfo.getDormitoryId());
          dto.setDormitoryName(dormitoryInfo.getName());
          rspDTOList.add(dto);
        }
      }
      return Result.success(rspDTOList);
    }
    return Result.success(new ArrayList<>());
  }

  @Override
  public Result<List<DormitoryClockStatDTO>> listDormitoryClockStatForApp(Long userId,
                                                                          Long buildingId,
                                                                          Integer floorNumber,
                                                                          Long dormitoryId,
                                                                          Boolean checkStatus,
                                                                          String orderBy,
                                                                          String descOrAsc) {

    List<DormitoryInfo> dormitoryInfoList = getDormitoryInfo(userId, buildingId, floorNumber, dormitoryId);
    if (CollectionUtils.isEmpty(dormitoryInfoList)) {
      return Result.success(new ArrayList<>());
    }
    List<Long> dormitoryIds = getDormitoryIds(dormitoryInfoList);
    //获取打卡设置
    ClockSetting clockSetting = clockSettingService.getClockSetting();
    //得到当前查寝日期
    long checkDay = ConvertUtil.getCurrCheckDormitoryDay(clockSetting);

    List<CheckDormitory> checkDormitoryList = checkDormitoryService.list(dormitoryIds, checkDay);
    Set<Long> sormitoryIdSet = getDormitoryIdSet(checkDormitoryList);
    List<DormitoryClockStatDTO> dormitoryClockStatDTOList = new ArrayList<>();
    Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();

    Map<Long, List<Long>> dormitoryUserListMap = getUserIdsByDormitoryUserList(userId, dormitoryIds);

    for (DormitoryInfo dormitoryInfo : dormitoryInfoList) {
      DormitoryClockStatDTO dto = new DormitoryClockStatDTO();
      dto.setBuildingId(dormitoryInfo.getBuildingId());
      BuildingInfo buildingInfo = buildingInfoMap.get(dto.getBuildingId());
      if (buildingInfo != null) {
        dto.setBuildingName(buildingInfo.getName());
      }
      dto.setDormitoryId(dormitoryInfo.getDormitoryId());
      dto.setDormitoryName(dormitoryInfo.getName());
      dto.setHasChecked(sormitoryIdSet.contains(dormitoryInfo.getDormitoryId()));
      if (checkStatus != null && checkStatus != dto.isHasChecked()) {
        continue;
      }

      List<Long> sIds = dormitoryUserListMap.get(dormitoryInfo.getDormitoryId());

      Map<String, Object> queryMap = new HashMap<>();
      queryMap.put("studentIds", sIds);
      queryMap.put("clockDate", checkDay);

      List<ClockStatByStatusDO> clockStatByStatusDOList = null;
      if (!CollectionUtils.isEmpty(sIds)) {
        clockStatByStatusDOList = studentClockService.statByStatus(queryMap);
      }
      dto.setTotalStudent(sIds != null ? sIds.size() : 0);

      if (!CollectionUtils.isEmpty(clockStatByStatusDOList)) {
        for (ClockStatByStatusDO c : clockStatByStatusDOList) {
          if (ClockStatus.STAYOUT.getType() == c.getClockStatus()) {
            dto.setLayOutStudent(c.getStatCount());
          } else if (ClockStatus.STAYOUT_LATE.getType() == c.getClockStatus()) {
            dto.setLayOutLayStudent(c.getStatCount());
          }
        }
      }
      dormitoryClockStatDTOList.add(dto);
    }

    //排序
    if (!CollectionUtils.isEmpty(dormitoryClockStatDTOList)) {
      if ("dormitoryCode".equals(orderBy)) {
        dormitoryClockStatDTOList.sort(new DormitoryClockStatCompatator01());
      } else if ("stayOutNum".equals(orderBy)) {
        dormitoryClockStatDTOList.sort(new DormitoryClockStatCompatator02());
      } else if ("stayOutLateNum".equals(orderBy)) {
        dormitoryClockStatDTOList.sort(new DormitoryClockStatCompatator03());
      }
    }

    //升序或降序 desc降序，asc升序
    if ("desc".equals(descOrAsc)) {
      Collections.reverse(dormitoryClockStatDTOList);
    }

    return Result.success(dormitoryClockStatDTOList);
  }

  @Override
  public Result<List<DormitoryStudentStatRspDTO>> getDormitoryClockDetailStatForApp(Long userId, Long dormitoryId) {
    List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByDormitoryId(dormitoryId);
    if (CollectionUtils.isEmpty(dormitoryUserList)) {
      return Result.success(Collections.emptyList());
    }
    List<Long> userIds = ConvertUtil.getUserIdsFromDormitoryUserList(dormitoryUserList);
    //获取打卡设置
    ClockSetting clockSetting = clockSettingService.getClockSetting();
    //得到当前查寝日期
    long checkDay = ConvertUtil.getCurrCheckDormitoryDay(clockSetting);

    List<Long> studentIds = getStudentIds(dormitoryUserList);
    List<StudentClock> studentClockList = studentClockService.list(studentIds, checkDay);
    Map<Long, StudentClock> studentIdToStatusMap = getStudentIdToStatusMap(studentClockList);

    List<DormitoryStudentStatRspDTO> dormitoryStudentStatRspDTOS = new ArrayList<>();
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      List<User> userList = userService.selectByPrimaryKeyList(userIds);
      Map<Long, User> userMap = ConvertUtil.getUserMap(userList);

      for (DormitoryUser dormitoryUser : dormitoryUserList) {
        DormitoryStudentStatRspDTO dto = new DormitoryStudentStatRspDTO();
        dto.setStudentId(dormitoryUser.getUserId());
        dto.setBedCode(dormitoryUser.getBedCode());
        User user = userMap.get(dormitoryUser.getUserId());
        if (user != null) {
          dto.setStudentName(user.getUserName());
          dto.setStudentCode(user.getCode());
          dto.setProfilePhoto(user.getHeadPortraitPath());
        }
        StudentClock studentClock = studentIdToStatusMap.get(dormitoryUser.getUserId());
        if (studentClock == null) {
          dto.setClockStatus(ClockStatus.NOT_CLOCK.getType());
        } else {
          dto.setClockStatus(studentClock.getClockStatus());
        }
        dormitoryStudentStatRspDTOS.add(dto);
      }
    }

    return Result.success(dormitoryStudentStatRspDTOS);
  }


  @Override
  public Result<DormitoryCheckDayStatRspDTO> dayStat(Long userId, Integer year, Integer month, Integer day) {
    int totalCount = 0;
    Map<String, Object> queryMap = new HashMap<>();
    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();

    queryMap.put("clockDate", DateUtil.ymdToint(year, month, day));
    if (instructorIds != null && instructorIds.contains(userId)) {
      //总人数
      totalCount = studentInfoService.countClockStudentByInstructorId(userId);
      queryMap.put("instructorId", userId);

    } else if (RoleType.DormitoryAdmin.getType() == roleType) {
      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
      List<Long> buildingIds = getBuildingIds(userBuildingRefList);
      totalCount = studentInfoService.countClockStudentByBuildingIds(buildingIds);
      queryMap.put("buildingIds", buildingIds);

    } else if (RoleType.StudentsAffairsAdmin.getType() == roleType) {
      totalCount = studentInfoService.countAllClockStudent();
    }
    List<ClockStatByStatusDO> clockStatByStatusDOList = studentClockService.statByStatus(queryMap);
    Map<Byte, Integer> statusCountMap = getStatusCountMap(clockStatByStatusDOList);

    DormitoryCheckDayStatRspDTO dto = new DormitoryCheckDayStatRspDTO();
    dto.setTotalNum(totalCount);
    dto.setClockNum(statusCountMap.get(ClockStatus.CLOCK.getType()) != null ? statusCountMap.get(ClockStatus.CLOCK.getType()) : 0);
    dto.setStayOutNum(statusCountMap.get(ClockStatus.STAYOUT.getType()) != null ? statusCountMap.get(ClockStatus.STAYOUT.getType()) : 0);
    dto.setStayOutLateNum(statusCountMap.get(ClockStatus.STAYOUT_LATE.getType()) != null ? statusCountMap.get(ClockStatus.STAYOUT_LATE.getType()) : 0);

    return Result.success(dto);
  }

  @Override
  public Result<List<DormitoryCheckDayStatListRspDTO>> dayStatStudentList(Long userId, Integer year, Integer month, Integer day, Byte clockStatus) {
    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();
    List<Long> studentIds = null;
    if (instructorIds != null && instructorIds.contains(userId)) {
      //总人数
      studentIds = studentInfoService.listClockStudentByInstructorId(userId);

    } else if (RoleType.DormitoryAdmin.getType() == roleType) {
      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
      List<Long> buildingIds = getBuildingIds(userBuildingRefList);
      studentIds = studentInfoService.listClockStudentByBuildingIds(buildingIds);

    } else if (RoleType.StudentsAffairsAdmin.getType() == roleType) {
    } else {
      return Result.success(Collections.emptyList());
    }
    if (RoleType.StudentsAffairsAdmin.getType() != roleType && CollectionUtils.isEmpty(studentIds)) {
      return Result.success(Collections.emptyList());
    }
    List<Long> resultIds =
        studentClockService.listStudentIdsByIdsAndStatusAndDate(studentIds, DateUtil.ymdToint(year, month, day), clockStatus);
    if (CollectionUtils.isEmpty(resultIds)) {
      return Result.success(Collections.emptyList());
    }
    List<User> userList = userService.selectByPrimaryKeyList(resultIds);
    List<DormitoryCheckDayStatListRspDTO> dormitoryCheckDayStatListRspDTOS = new ArrayList<>();
    if (!CollectionUtils.isEmpty(userList)) {
      for (User user : userList) {
        DormitoryCheckDayStatListRspDTO dto = new DormitoryCheckDayStatListRspDTO();
        dto.setStudentId(user.getUserId());
        dto.setStudentName(user.getUserName());
        dormitoryCheckDayStatListRspDTOS.add(dto);
      }
    }

    return Result.success(dormitoryCheckDayStatListRspDTOS);
  }

  private Map<Byte, Integer> getStatusCountMap(List<ClockStatByStatusDO> clockStatByStatusDOList) {
    Map<Byte, Integer> statusCountMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(clockStatByStatusDOList)) {
      for (ClockStatByStatusDO d : clockStatByStatusDOList) {
        statusCountMap.put(d.getClockStatus(), d.getStatCount());
      }
    }
    return statusCountMap;
  }

  @Override
  public Result<DormitoryCheckWeekStatRspDTO> weekStat(Long userId, Integer weekNumber) {
    int totalCount = 0;
    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();
    DormitoryCheckWeekStatRspDTO dto = new DormitoryCheckWeekStatRspDTO();
    if (instructorIds != null && instructorIds.contains(userId)) {
      //总人数
      totalCount = studentInfoService.countClockStudentByInstructorId(userId);

    } else if (RoleType.DormitoryAdmin.getType() == roleType) {
      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
      List<Long> buildingIds = getBuildingIds(userBuildingRefList);
      totalCount = studentInfoService.countClockStudentByBuildingIds(buildingIds);

    } else if (RoleType.StudentsAffairsAdmin.getType() == roleType) {
      totalCount = studentInfoService.countAllClockStudent();
    }
    dto.setTotalNum(totalCount);
    TermConfig termConfig = termConfigService.getLastTermConfig();
    if (termConfig == null) {
      logger.warn("不在学期内");
      return Result.success(dto);
    }
    Date startDate = termConfig.getStartDate();
    Date endDate = termConfig.getEndDate();
    WeekInfoRspDTO weekInfoRspDTO = ConvertUtil.getWeek(startDate, endDate, weekNumber);
    if (weekInfoRspDTO == null) {
      logger.warn("周数不存在");
      return Result.success(dto);
    }

    Map<String, Object> queryMap = new HashMap<>();
    Date startStatDate = DateUtil.add(weekInfoRspDTO.getStartDate(), Calendar.DAY_OF_YEAR, -1);
    Date endStatDate = DateUtil.add(weekInfoRspDTO.getEndDate(), Calendar.DAY_OF_YEAR, -1);

    List<ClockDaySetting> clockDaySettingList = clockDaySettingService.list(startStatDate, endStatDate);
    if (CollectionUtils.isEmpty(clockDaySettingList)) {
      logger.warn("本周不需要打卡");
      return Result.success(dto);
    }

    Date yestoday = DateUtil.add(new Date(), Calendar.DAY_OF_YEAR, -1);
    long currDate = DateUtil.getYearMonthDayByDate(yestoday);
    if (DateUtil.getYearMonthDayByDate(endStatDate) >= currDate) {
      endStatDate = DateUtil.getDateEndTime(yestoday);
    }
    queryMap.put("startClockDate", startStatDate);
    queryMap.put("endClockDate", endStatDate);

    if (instructorIds != null && instructorIds.contains(userId)) {
      //总人数
      queryMap.put("instructorId", userId);

    } else if (RoleType.DormitoryAdmin.getType() == roleType) {
      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
      List<Long> buildingIds = getBuildingIds(userBuildingRefList);
      queryMap.put("buildingIds", buildingIds);

    }
    List<UserClockCountStatDO> userClockCountStatDOS = studentClockService.statByUserStatus(queryMap);
    dto.setStayOutNum(userClockCountStatDOS == null ? 0 : (int) userClockCountStatDOS.stream().filter(e -> ClockStatus.STAYOUT.getType() == e.getClockStatus()).count());
    dto.setStayOutLateNum(userClockCountStatDOS == null ? 0 : (int) userClockCountStatDOS.stream().filter(e -> ClockStatus.STAYOUT_LATE.getType() == e.getClockStatus()).count());
    return Result.success(dto);
  }

  @Override
  public Result<List<DormitoryCheckWeekStatListRspDTO>> weekStatStudentList(Long userId, Integer weekNumber, Byte clockStatus) {
    TermConfig termConfig = termConfigService.getLastTermConfig();
    if (termConfig == null) {
      logger.warn("不在学期内");
      return Result.success(Collections.emptyList());
    }
    Date startDate = termConfig.getStartDate();
    Date endDate = termConfig.getEndDate();
    WeekInfoRspDTO weekInfoRspDTO = ConvertUtil.getWeek(startDate, endDate, weekNumber);
    if (weekInfoRspDTO == null) {
      logger.warn("周数不存在");
      return Result.success(Collections.emptyList());
    }
    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();

    List<Long> studentIds = null;
    Date startStatDate = DateUtil.add(weekInfoRspDTO.getStartDate(), Calendar.DAY_OF_YEAR, -1);
    Date endStatDate = DateUtil.add(weekInfoRspDTO.getEndDate(), Calendar.DAY_OF_YEAR, -1);

    List<ClockDaySetting> clockDaySettingList = clockDaySettingService.list(startStatDate, endStatDate);
    if (CollectionUtils.isEmpty(clockDaySettingList)) {
      logger.warn("本周不需要打卡");
      return Result.success(Collections.emptyList());
    }

    Date yestoday = DateUtil.add(new Date(), Calendar.DAY_OF_YEAR, -1);
    long currDate = DateUtil.getYearMonthDayByDate(yestoday);
    if (DateUtil.getYearMonthDayByDate(endStatDate) >= currDate) {
      endStatDate = DateUtil.getDateEndTime(yestoday);
    }

    if (instructorIds != null && instructorIds.contains(userId)) {
      //总人数
      studentIds = studentInfoService.listClockStudentByInstructorId(userId);
    } else if (RoleType.DormitoryAdmin.getType() == roleType) {
      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
      List<Long> buildingIds = getBuildingIds(userBuildingRefList);
      studentIds = studentInfoService.listClockStudentByBuildingIds(buildingIds);
    } else if (RoleType.StudentsAffairsAdmin.getType() == roleType) {
    } else {
      return Result.success(Collections.emptyList());
    }
    if (RoleType.StudentsAffairsAdmin.getType() != roleType && CollectionUtils.isEmpty(studentIds)) {
      return Result.success(Collections.emptyList());
    }

    List<User> userList = userService.selectByPrimaryKeyList(studentIds);
    Map<Long, User> userMap = ConvertUtil.getUserMap(userList);
    List<StudentClockStatusCountStatDO> studentClockStatusCountStatDOS =
        studentClockService.listStudentClockStatusCountStat(studentIds, startStatDate, endStatDate, clockStatus);

    List<DormitoryCheckWeekStatListRspDTO> dormitoryCheckWeekStatListRspDTOS = new ArrayList<>();
    if (!CollectionUtils.isEmpty(studentClockStatusCountStatDOS)) {
      for (StudentClockStatusCountStatDO d : studentClockStatusCountStatDOS) {
        DormitoryCheckWeekStatListRspDTO dto = new DormitoryCheckWeekStatListRspDTO();
        dto.setStudentId(d.getStudentId());
        dto.setCount(d.getStatCount());
        User user = userMap.get(d.getStudentId());
        if (user != null) {
          dto.setStudentName(user.getUserName());
        }
        dormitoryCheckWeekStatListRspDTOS.add(dto);
      }
    }

    return Result.success(dormitoryCheckWeekStatListRspDTOS);
  }

  @Override
  public Result addDormitoryCheck(DormitoryCheckOverReqDTO reqDTO) {

    //获取打卡设置
    ClockSetting clockSetting = clockSettingService.getClockSetting();
    //得到当前查寝日期
    long clockDate = ConvertUtil.getCurrCheckDormitoryDay(clockSetting);
    CheckDormitory checkDormitory = new CheckDormitory();
    checkDormitory.setDormitoryId(reqDTO.getDormitoryId());
    checkDormitory.setOperatorId(reqDTO.getOperatorId());
    checkDormitory.setOperatorName(reqDTO.getOperatorName());
    checkDormitory.setOperateDate(new Date());
    checkDormitory.setStatDate(clockDate);
    checkDormitory.setId(DateUtil.uuid());
    checkDormitoryService.insert(checkDormitory);
    return Result.success();
  }

  @Override
  public Result<List<StudentDormitoryRsqDTO>> queryStudent(Long userId, String nameOrCode) {
    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();
    List<Long> studentIds = null;
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    if (instructorIds != null && instructorIds.contains(userId)) {
      //总人数
      studentIds = studentInfoService.listStudentIdsByInstructorIdAndNOC(userId, nameOrCode);

    } else if (RoleType.DormitoryAdmin.getType() == roleType) {
      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
      List<Long> buildingIds = getBuildingIds(userBuildingRefList);
      studentIds = studentInfoService.listClockStudentByBuildingIdsAndNOC(buildingIds, nameOrCode);

    } else if (RoleType.StudentsAffairsAdmin.getType() == roleType) {
      studentIds = studentInfoService.listClockStudentByNOC(nameOrCode);
    } else {
      return Result.success(Collections.emptyList());
    }
    if (CollectionUtils.isEmpty(studentIds)) {
      return Result.success(Collections.emptyList());
    }

    List<User> userList = userService.selectByPrimaryKeyList(studentIds);
    List<StudentDormitoryRsqDTO> studentDormitoryRsqDTOS = new ArrayList<>();
    if (!CollectionUtils.isEmpty(userList)) {
      for (User user : userList) {
        StudentDormitoryRsqDTO dto = new StudentDormitoryRsqDTO();
        dto.setStudentId(user.getUserId());
        dto.setName(user.getUserName());
        dto.setCode(user.getCode());
        studentDormitoryRsqDTOS.add(dto);
      }
    }
    return Result.success(studentDormitoryRsqDTOS);
  }

  private List<Long> getBuildingIds(List<UserBuildingRef> userBuildingRefList) {
    if (!CollectionUtils.isEmpty(userBuildingRefList)) {
      return userBuildingRefList.stream().map(e -> e.getBuildingId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  private Set<Long> getDormitoryIdSet(List<CheckDormitory> checkDormitoryList) {
    if (!CollectionUtils.isEmpty(checkDormitoryList)) {
      return checkDormitoryList.stream().map(e -> e.getDormitoryId()).collect(Collectors.toSet());
    }
    return Collections.EMPTY_SET;
  }

  private Map<Long, StudentClock> getStudentIdToStatusMap(List<StudentClock> studentClockList) {
    if (!CollectionUtils.isEmpty(studentClockList)) {
      return studentClockList.stream().collect(Collectors.toMap(StudentClock::getUserId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  private List<Long> getStudentIds(List<DormitoryUser> dormitoryUserList) {
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      return dormitoryUserList.stream().map(e -> e.getUserId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * 从dormitoryInfoList获取DormitoryIds
   *
   * @param dormitoryInfoList :
   * @return : java.util.List<java.lang.Long>
   */
  private List<Long> getDormitoryIds(List<DormitoryInfo> dormitoryInfoList) {
    if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
      return dormitoryInfoList.stream().map(e -> e.getDormitoryId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  private BuildingQueryRspDTO convertToDormitoryBuildingQueryRspDTO(BuildingInfo buildingInfo) {
    BuildingQueryRspDTO dto = new BuildingQueryRspDTO();
    dto.setBuildingId(buildingInfo.getBuildingId());
    dto.setBuildingName(buildingInfo.getName());
    dto.setFloorNumber(buildingInfo.getTotalFloor());
    return dto;
  }


  private DormitorySimpleRspDTO convertToDormitoryQueryRspDTO(DormitoryInfo dormitoryInfo) {
    DormitorySimpleRspDTO dto = new DormitorySimpleRspDTO();
    dto.setDormitoryId(dormitoryInfo.getDormitoryId());
    dto.setDormitoryName(dormitoryInfo.getName());
    return dto;
  }

  /**
   * 获取宿舍列表
   *
   * @param userId      :
   * @param buildingId  :
   * @param floorNumber :
   * @return : java.util.List<com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo>
   */
  private List<DormitoryInfo> getDormitoryInfo(Long userId, Long buildingId, Integer floorNumber, Long dormitoryId) {
    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();

    List<DormitoryInfo> dormitoryInfoList = null;
    if (instructorIds != null && instructorIds.contains(userId)) {
      dormitoryInfoList = dormitoryInfoService.listDormitoryByInstructorId(userId);
      if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
        Iterator<DormitoryInfo> dormitoryInfoIterable = dormitoryInfoList.iterator();
        while (dormitoryInfoIterable.hasNext()) {
          DormitoryInfo dormitoryInfo = dormitoryInfoIterable.next();
          if (!dormitoryInfo.getBuildingId().equals(buildingId)) {
            dormitoryInfoIterable.remove();
            continue;
          }
          if (floorNumber != null && dormitoryInfo.getFloorNumber().intValue() != floorNumber) {
            dormitoryInfoIterable.remove();
            continue;
          }
        }
      }
    } else if (RoleType.DormitoryAdmin.getType() == roleType) {

      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
      if (!CollectionUtils.isEmpty(userBuildingRefList)) {
        List<Long> buildingIds = userBuildingRefList.stream().map(e -> e.getBuildingId()).collect(Collectors.toList());
        if (buildingId != null) {
          if (buildingIds.contains(buildingId)) {
            buildingIds.clear();
            buildingIds.add(buildingId);
          } else {
            return Collections.EMPTY_LIST;
          }
        }
        dormitoryInfoList = dormitoryInfoService.list1(buildingIds, floorNumber);
      }

    } else if (RoleType.StudentsAffairsAdmin.getType() == roleType) {
      dormitoryInfoList = dormitoryInfoService.list(buildingId, floorNumber);
    }
    if (!CollectionUtils.isEmpty(dormitoryInfoList) && dormitoryId != null) {
      Iterator<DormitoryInfo> dormitoryInfoIterable = dormitoryInfoList.iterator();
      while (dormitoryInfoIterable.hasNext()) {
        DormitoryInfo dormitoryInfo = dormitoryInfoIterable.next();
        if (!dormitoryInfo.getDormitoryId().equals(dormitoryId)) {
          dormitoryInfoIterable.remove();
        }
      }
    }
    return dormitoryInfoList;
  }

  /**
   * 根据用户ID和宿舍获取负责学生列表
   *
   * @param userId       :
   * @param dormitoryIds :
   * @return : java.util.List<java.lang.Long>
   */
  private Map<Long, List<Long>> getUserIdsByDormitoryUserList(Long userId, List<Long> dormitoryIds) {

    List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByDormitoryIds(dormitoryIds);

    List<Long> instructorIds = classCacheService.getInstructorIds();
    if (instructorIds != null && instructorIds.contains(userId)) {
      List<Long> studentIds = studentInfoService.listStudentIdsByInstructorIdAndNOC(userId, null);
      if (CollectionUtils.isEmpty(studentIds)) {
        return null;
      }
      if (!CollectionUtils.isEmpty(dormitoryUserList)) {
        Iterator<DormitoryUser> dormitoryUserIterator = dormitoryUserList.iterator();
        while (dormitoryUserIterator.hasNext()) {
          DormitoryUser dormitoryUser = dormitoryUserIterator.next();
          if (!studentIds.contains(dormitoryUser.getUserId())) {
            dormitoryUserIterator.remove();
          }
        }
      }
    }

    return ConvertUtil.getDormitoryUserListMap(dormitoryUserList);
  }


}
