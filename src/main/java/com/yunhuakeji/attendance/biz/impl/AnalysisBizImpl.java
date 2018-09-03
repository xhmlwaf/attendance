package com.yunhuakeji.attendance.biz.impl;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.biz.AnalysisBiz;
import com.yunhuakeji.attendance.biz.CommonHandlerUtil;
import com.yunhuakeji.attendance.biz.CommonQueryUtil;
import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.cache.*;
import com.yunhuakeji.attendance.comparator.*;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.*;
import com.yunhuakeji.attendance.dao.bizdao.model.*;
import com.yunhuakeji.attendance.dto.response.*;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.service.baseservice.DormitoryUserService;
import com.yunhuakeji.attendance.service.baseservice.StudentInfoService;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.TermConfigService;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.ListUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class AnalysisBizImpl implements AnalysisBiz {

  private static final Logger logger = LoggerFactory.getLogger(AnalysisBizImpl.class);

  @Autowired
  private StudentClockService studentClockService;

  @Autowired
  private ClockDaySettingService clockDaySettingService;

  @Autowired
  private MajorCacheService majorCacheService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserClassService userClassService;

  @Autowired
  private OrgCacheService orgCacheService;

  @Autowired
  private DormitoryUserService dormitoryUserService;

  @Autowired
  private DormitoryCacheService dormitoryCacheService;

  @Autowired
  private BuildingCacheService buildingCacheService;

  @Autowired
  private TermConfigService termConfigService;

  @Autowired
  private StudentInfoService studentInfoService;

  /**
   * 按学院时间统计晚归和未归
   *
   * @param orgId :
   * @param date  :
   * @return : com.yunhuakeji.attendance.constants.Result<com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByDayRsqDTO>
   */
  @Override
  public Result<AnalysisExceptionStatByDayRsqDTO> getAnalysisExceptionStatByDay(Long orgId, Date date) {
    Map<String, Object> queryMap = new HashMap<>();
    queryMap.put("clockDate", DateUtil.getYearMonthDayByDate(date));
    if (orgId != null) {
      queryMap.put("orgId", orgId);
    }
    List<ClockStatByStatusDO> clockStatByStatusDOList = studentClockService.statByStatus(queryMap);
    AnalysisExceptionStatByDayRsqDTO dto = new AnalysisExceptionStatByDayRsqDTO();
    if (!CollectionUtils.isEmpty(clockStatByStatusDOList)) {
      for (ClockStatByStatusDO clockStatByStatusDO : clockStatByStatusDOList) {
        if (ClockStatus.STAYOUT.getType() == clockStatByStatusDO.getClockStatus()) {
          dto.setLastNightStayoutNum(clockStatByStatusDO.getStatCount());
        } else if (ClockStatus.STAYOUT_LATE.getType() == clockStatByStatusDO.getClockStatus()) {
          dto.setLastNightStayoutLateNum(clockStatByStatusDO.getStatCount());
        }
      }
    }
    return Result.success(dto);
  }


  @Override
  public PagedResult<AnalysisExceptionClockByDayRsqDTO> getAnalysisExceptionClockByDay(String nameOrCode,
                                                                                       Long orgId,
                                                                                       Long majorId,
                                                                                       Long instructor,
                                                                                       Byte clockStatus,
                                                                                       Date date,
                                                                                       String orderBy,
                                                                                       String descOrAsc,
                                                                                       Integer pageNo,
                                                                                       Integer pageSize) {
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    //统计从指定日往前一个月的打卡日期
    List<ClockDaySetting> clockDaySettingList =
        clockDaySettingService.list(DateUtil.add(date, Calendar.DAY_OF_YEAR, 30), date);

    //算出从指定日开始的连续打卡日期
    List<ClockDaySetting> lxList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(clockDaySettingList)) {
      clockDaySettingList.sort(new ClockDaySettingCompatator01());
      Collections.reverse(clockDaySettingList);
      for (int i = 0; i < clockDaySettingList.size(); i++) {
        if (ConvertUtil.dateEqual(clockDaySettingList.get(i), DateUtil.add(date, Calendar.DAY_OF_YEAR, -1 * i))) {
          lxList.add(clockDaySettingList.get(i));
        } else {
          break;
        }
      }
    }
    List<Long> orgClassIds = CommonQueryUtil.getClassIdsByOrgId(orgId);
    List<Long> majorClassIds = CommonQueryUtil.getClassIdsByMajorId(majorId);
    List<Long> instructorClassIds = CommonQueryUtil.getClassIdsByInstructorId(instructor);
    List<Long> lastClassIds = null;
    if (!CollectionUtils.isEmpty(orgClassIds)) {
      lastClassIds = orgClassIds;
      if (!CollectionUtils.isEmpty(majorClassIds)) {
        lastClassIds.retainAll(majorClassIds);
      }
      if (!CollectionUtils.isEmpty(instructorClassIds)) {
        lastClassIds.retainAll(instructorClassIds);
      }
    } else if (!CollectionUtils.isEmpty(majorClassIds)) {
      lastClassIds = majorClassIds;
      if (!CollectionUtils.isEmpty(instructorClassIds)) {
        lastClassIds.retainAll(instructorClassIds);
      }
    } else if (!CollectionUtils.isEmpty(instructorClassIds)) {
      lastClassIds = instructorClassIds;
    }

    //根据classId和状态查询学生昨天的状态
    if (orgId != null || majorId != null || instructor != null) {
      if (CollectionUtils.isEmpty(lastClassIds)) {
        return PagedResult.success(pageNo, pageSize);
      }
    }
    List<Byte> clockStatusList = new ArrayList<>();
    if (clockStatus != null) {
      clockStatusList.add(clockStatus);
    } else {
      //默认查询晚归未归的学生
      clockStatusList.add(ClockStatus.STAYOUT.getType());
      clockStatusList.add(ClockStatus.STAYOUT_LATE.getType());
    }
    List<StudentClockStatusDO> studentClockStatusDOList =
        studentClockService.statStudentClockStatus(nameOrCode, lastClassIds, null, DateUtil.getYearMonthDayByDate(date), clockStatusList);
    if (CollectionUtils.isEmpty(studentClockStatusDOList)) {
      return PagedResult.success(pageNo, pageSize);
    }
    List<Long> needQueryList = new ArrayList<>();
    for (StudentClockStatusDO i : studentClockStatusDOList) {
      if (i.getClockStatus() != null && ClockStatus.STAYOUT.getType() == i.getClockStatus()) {
        needQueryList.add(i.getStudentId());
        i.setLxStayOut(1);
      } else if (i.getClockStatus() != null && ClockStatus.STAYOUT_LATE.getType() == i.getClockStatus()) {
        needQueryList.add(i.getStudentId());
        i.setLxStayOutLate(1);
      }
    }
    if (lxList != null && lxList.size() > 1) {
      for (int i = 1; i < lxList.size(); i++) {
        if (!CollectionUtils.isEmpty(needQueryList)) {
          break;
        }
        ClockDaySetting clockDaySetting = lxList.get(i);
        studentClockStatusDOList =
            studentClockService.statStudentClockStatus(nameOrCode, null, needQueryList,
                DateUtil.ymdTolong(clockDaySetting.getYearMonth(), clockDaySetting.getDay()), clockStatusList);
        if (!CollectionUtils.isEmpty(studentClockStatusDOList)) {
          needQueryList.clear();
          for (StudentClockStatusDO x : studentClockStatusDOList) {
            if (x.getClockStatus() != null && ClockStatus.STAYOUT.getType() == x.getClockStatus()) {
              needQueryList.add(x.getStudentId());
              x.setLxStayOut(x.getLxStayOut());
            } else if (x.getClockStatus() != null && ClockStatus.STAYOUT_LATE.getType() == x.getClockStatus()) {
              needQueryList.add(x.getStudentId());
              x.setLxStayOutLate(x.getLxStayOutLate());
            }
          }
        } else {
          break;
        }
      }
    }
    //连续未归：continuousStayoutDays 连续晚归：continuousStayoutLateDays
    if ("continuousStayoutDays".equals(orderBy)) {
      studentClockStatusDOList.sort(new StudentClockStatusCompatator01());
    } else if ("continuousStayoutLateDays".equals(orderBy)) {
      studentClockStatusDOList.sort(new StudentClockStatusCompatator02());
    }

    //desc降序，asc升序
    if ("desc".equals(descOrAsc)) {
      Collections.reverse(studentClockStatusDOList);
    }

    PageInfo<StudentClockStatusDO> pageInfo = ListUtil.getPagingResultMap(studentClockStatusDOList, pageNo, pageSize);
    if (CollectionUtils.isEmpty(pageInfo.getList())) {
      return PagedResult.success(pageNo, pageSize);
    }

    List<AnalysisExceptionClockByDayRsqDTO> analysisExceptionClockByDayRsqDTOS = new ArrayList<>();
    List<Long> userIds = ConvertUtil.getUserIdsByStudentClockStatus(pageInfo.getList());
    List<User> userList = userService.selectByPrimaryKeyList(userIds);
    Map<Long, User> userMap = ConvertUtil.getUserMap(userList);
    List<UserClass> userClassList = userClassService.listByUserIds(userIds);
    Map<Long, Long> userClassMap = ConvertUtil.getUserClassMap(userClassList);
    Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();
    Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
    Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();
    Map<Long, DormitoryInfo> dormitoryInfoMap = dormitoryCacheService.getDormitoryMap();

    List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByUserIds(userIds);
    Map<Long, DormitoryUser> userDormitoryRefMap = ConvertUtil.getUserDormitoryRefMap(dormitoryUserList);

    Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();

    for (StudentClockStatusDO s : pageInfo.getList()) {
      AnalysisExceptionClockByDayRsqDTO dto = new AnalysisExceptionClockByDayRsqDTO();
      dto.setStudentId(s.getStudentId());
      if (s.getClockStatus() != null) {
        dto.setClockStatus(s.getClockStatus());
      } else {
        dto.setClockStatus(ClockStatus.NOT_CLOCK.getType());
      }
      dto.setContinuousStayoutDays(s.getLxStayOut());
      dto.setContinuousStayoutLateDays(s.getLxStayOutLate());
      User user = userMap.get(s.getStudentId());
      if (user != null) {
        dto.setProfilePhoto(user.getHeadPortraitPath());
        dto.setStudentName(user.getUserName());
        dto.setStudentCode(user.getCode());
      }
      Long classId = userClassMap.get(s.getStudentId());
      dto.setClassId(classId);
      ClassInfo classInfo = classInfoMap.get(classId);
      if (classInfo != null) {
        dto.setClassName(classInfo.getClassCode());
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
      DormitoryUser dormitoryUser = userDormitoryRefMap.get(s.getStudentId());
      if (dormitoryUser != null) {
        dto.setBedCode(dormitoryUser.getBedCode());
        dto.setDormitoryId(dormitoryUser.getDormitoryId());
        DormitoryInfo dormitoryInfo = dormitoryInfoMap.get(dormitoryUser.getDormitoryId());
        if (dormitoryInfo != null) {
          dto.setBuildingId(dormitoryInfo.getBuildingId());
          BuildingInfo buildingInfo = buildingInfoMap.get(dormitoryInfo.getBuildingId());
          if (buildingInfo != null) {
            dto.setBuildingName(buildingInfo.getName());
          }

        }
      }
      analysisExceptionClockByDayRsqDTOS.add(dto);
    }

    //3.组装返回结果
    Page<AnalysisExceptionClockByDayRsqDTO> analysisExceptionClockByDayRsqDTOPage = new Page<>();
    analysisExceptionClockByDayRsqDTOPage.setResult(analysisExceptionClockByDayRsqDTOS);
    analysisExceptionClockByDayRsqDTOPage.setTotalCount((int) pageInfo.getTotal());
    analysisExceptionClockByDayRsqDTOPage.setPageSize(pageSize);
    analysisExceptionClockByDayRsqDTOPage.setPageNo(pageNo);
    analysisExceptionClockByDayRsqDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(analysisExceptionClockByDayRsqDTOPage);

  }

  private List<Long> getUserIds(List<AnalysisExceptionClockByWeekRsqDTO> analysisExceptionClockByWeekRsqDTOS) {
    List<Long> userIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(analysisExceptionClockByWeekRsqDTOS)) {
      for (AnalysisExceptionClockByWeekRsqDTO i : analysisExceptionClockByWeekRsqDTOS) {
        userIds.add(i.getStudentId());
      }
    }
    return userIds;
  }


  private List<Long> getUserIds1(List<StudentClockStatusDO> studentClockStatusDOList) {
    List<Long> userIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(studentClockStatusDOList)) {
      for (StudentClockStatusDO i : studentClockStatusDOList) {
        userIds.add(i.getStudentId());
      }
    }
    return userIds;
  }


  @Override
  public Result<AnalysisExceptionStatByWeekRsqDTO> getAnalysisExceptionStatByWeek(Long orgId, int weekNumber) {
    AnalysisExceptionStatByWeekRsqDTO dto = new AnalysisExceptionStatByWeekRsqDTO();
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
    Date startStatDate = DateUtil.add(weekInfoRspDTO.getStartDate(), Calendar.DAY_OF_YEAR, -1);
    Date endStatDate = DateUtil.add(weekInfoRspDTO.getEndDate(), Calendar.DAY_OF_YEAR, -1);
    List<ClockDaySetting> clockDaySettingList = clockDaySettingService.list(startStatDate, endStatDate);
    if (CollectionUtils.isEmpty(clockDaySettingList)) {
      logger.warn("本周不需要打卡");
      return Result.success(dto);
    }

    Map<String, Object> queryMap = new HashMap<>();
    int clcokStudentNum = 0;
    if (orgId != null) {
      queryMap.put("orgId", orgId);
      List<Long> classIds = CommonQueryUtil.getClassIdsByOrgId(orgId);
      if (!CollectionUtils.isEmpty(classIds)) {
        clcokStudentNum = studentInfoService.countClockStudentByClassIds(classIds);
      }
    } else {
      clcokStudentNum = studentInfoService.countAllClockStudent();
    }
    dto.setWeekNormalNum(clcokStudentNum * clockDaySettingList.size());
    queryMap.put("startClockDate", startStatDate);
    queryMap.put("endClockDate", endStatDate);
    List<ClockStatByStatusDO> clockStatByStatusDOList = studentClockService.statByStatus(queryMap);
    if (!CollectionUtils.isEmpty(clockDaySettingList)) {
      for (ClockStatByStatusDO c : clockStatByStatusDOList) {
        if (ClockStatus.STAYOUT.getType() == c.getClockStatus()) {
          dto.setWeekStayoutNum(c.getStatCount());
        } else if (ClockStatus.STAYOUT_LATE.getType() == c.getClockStatus()) {
          dto.setWeekStayoutLateNum(c.getStatCount());
        }
      }
    }
    return Result.success(dto);
  }

  @Override
  public Result<List<AnalysisDayExceptionDTO>> getAnalysisExceptionStatListByWeek(Long orgId, int weekNumber) {

    TermConfig termConfig = termConfigService.getLastTermConfig();
    if (termConfig == null) {
      logger.warn("不在学期内");
      return Result.success();
    }
    Date startDate = termConfig.getStartDate();
    Date endDate = termConfig.getEndDate();
    WeekInfoRspDTO weekInfoRspDTO = ConvertUtil.getWeek(startDate, endDate, weekNumber);
    if (weekInfoRspDTO == null) {
      logger.warn("周数不存在");
      return Result.success();
    }
    Date startStatDate = DateUtil.add(weekInfoRspDTO.getStartDate(), Calendar.DAY_OF_YEAR, -1);
    Date endStatDate = DateUtil.add(weekInfoRspDTO.getEndDate(), Calendar.DAY_OF_YEAR, -1);
    List<ClockDaySetting> clockDaySettingList = clockDaySettingService.list(startStatDate, endStatDate);
    if (CollectionUtils.isEmpty(clockDaySettingList)) {
      logger.warn("本周不需要打卡.start:{},end:{}", DateUtil.dateToStr(startStatDate, DateUtil.DATESTYLE_YYYY_MM_DD),
          DateUtil.dateToStr(endStatDate, DateUtil.DATESTYLE_YYYY_MM_DD));
      return Result.success();
    }
    List<DateStatusCountStatDO> statusCountStatDOS =
        studentClockService.dateStatusCountStat(orgId, startStatDate, endStatDate);
    List<AnalysisDayExceptionDTO> analysisDayExceptionDTOList = new ArrayList<>();

    Map<Long, List<DateStatusCountStatDO>> map = getDateStatusCountStatMap(statusCountStatDOS);
    while (true) {
      if (DateUtil.getYearMonthDayByDate(startStatDate) > DateUtil.getYearMonthDayByDate(endStatDate)) {
        break;
      }
      AnalysisDayExceptionDTO dto = new AnalysisDayExceptionDTO();
      List<DateStatusCountStatDO> dateStatusCountStatDOList = map.get(DateUtil.getYearMonthDayByDate(startStatDate));
      dto.setDate(startStatDate);
      if (!CollectionUtils.isEmpty(dateStatusCountStatDOList)) {
        for (DateStatusCountStatDO d : dateStatusCountStatDOList) {
          if (ClockStatus.STAYOUT.getType() == d.getClockStatus()) {
            dto.setStayoutNum(d.getStatCount());
          } else if (ClockStatus.STAYOUT_LATE.getType() == d.getClockStatus()) {
            dto.setStayoutLateNum(d.getStatCount());
          }
        }
      }
      startStatDate = DateUtil.add(startStatDate, Calendar.DAY_OF_YEAR, 1);
      analysisDayExceptionDTOList.add(dto);
    }

    return Result.success(analysisDayExceptionDTOList);
  }

  private Map<Long, List<DateStatusCountStatDO>> getDateStatusCountStatMap(List<DateStatusCountStatDO> statusCountStatDOS) {
    Map<Long, List<DateStatusCountStatDO>> map = new HashMap<>();
    if (!CollectionUtils.isEmpty(statusCountStatDOS)) {
      for (DateStatusCountStatDO d : statusCountStatDOS) {
        List<DateStatusCountStatDO> dateStatusCountStatDOS = map.get(d.getStatDate());
        if (dateStatusCountStatDOS == null) {
          dateStatusCountStatDOS = new ArrayList<>();
        }
        dateStatusCountStatDOS.add(d);
        map.put(d.getStatDate(), dateStatusCountStatDOS);
      }
    }
    return map;

  }

  @Override
  public PagedResult<AnalysisExceptionClockByWeekRsqDTO> getAnalysisExceptionClockByWeek(String nameOrCode,
                                                                                         Long orgId,
                                                                                         Long majorId,
                                                                                         Long instructor,
                                                                                         int weekNum,
                                                                                         String orderBy,
                                                                                         String descOrAsc,
                                                                                         Integer pageNo,
                                                                                         Integer pageSize) {
    List<Long> orgClassIds = CommonQueryUtil.getClassIdsByOrgId(orgId);
    List<Long> majorClassIds = CommonQueryUtil.getClassIdsByMajorId(majorId);
    List<Long> instructorClassIds = CommonQueryUtil.getClassIdsByInstructorId(instructor);
    List<Long> lastClassIds = null;
    if (!CollectionUtils.isEmpty(orgClassIds)) {
      lastClassIds = orgClassIds;
      if (!CollectionUtils.isEmpty(majorClassIds)) {
        lastClassIds.retainAll(majorClassIds);
      }
      if (!CollectionUtils.isEmpty(instructorClassIds)) {
        lastClassIds.retainAll(instructorClassIds);
      }
    } else if (!CollectionUtils.isEmpty(majorClassIds)) {
      lastClassIds = majorClassIds;
      if (!CollectionUtils.isEmpty(instructorClassIds)) {
        lastClassIds.retainAll(instructorClassIds);
      }
    } else if (!CollectionUtils.isEmpty(instructorClassIds)) {
      lastClassIds = instructorClassIds;
    }
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    TermConfig termConfig = termConfigService.getLastTermConfig();
    if (termConfig == null) {
      logger.warn("不在学期内");
      return PagedResult.success(pageNo, pageSize);
    }
    Date startDate = termConfig.getStartDate();
    Date endDate = termConfig.getEndDate();
    WeekInfoRspDTO weekInfoRspDTO = ConvertUtil.getWeek(startDate, endDate, weekNum);
    if (weekInfoRspDTO == null) {
      logger.warn("周数不存在");
      return PagedResult.success(pageNo, pageSize);
    }
    Date startStatDate = DateUtil.add(weekInfoRspDTO.getStartDate(), Calendar.DAY_OF_YEAR, -1);
    Date endStatDate = DateUtil.add(weekInfoRspDTO.getEndDate(), Calendar.DAY_OF_YEAR, -1);
    List<ClockDaySetting> clockDaySettingList = clockDaySettingService.list(startStatDate, endStatDate);
    if (CollectionUtils.isEmpty(clockDaySettingList)) {
      logger.warn("本周不需要打卡");
      return PagedResult.success(pageNo, pageSize);
    }

    List<StudentStatusCountDO> studentStatusCountDOList =
        studentClockService.studentStatusCountStat(nameOrCode, lastClassIds, startStatDate, endStatDate);
    List<AnalysisExceptionClockByWeekRsqDTO> analysisExceptionClockByWeekRsqDTOS = new ArrayList<>();
    Map<Long, List<StudentStatusCountDO>> map = ConvertUtil.getStudentStatusCountMap(studentStatusCountDOList);
    if (!CollectionUtils.isEmpty(map)) {
      for (Map.Entry<Long, List<StudentStatusCountDO>> entry : map.entrySet()) {
        AnalysisExceptionClockByWeekRsqDTO dto = new AnalysisExceptionClockByWeekRsqDTO();
        dto.setStudentId(entry.getKey());
        List<StudentStatusCountDO> studentStatusCountDOList1 = entry.getValue();
        if (!CollectionUtils.isEmpty(studentStatusCountDOList1)) {
          for (StudentStatusCountDO s : studentStatusCountDOList1) {
            if (s.getClockStatus() != null && ClockStatus.STAYOUT.getType() == s.getClockStatus()) {
              dto.setStayoutDays(s.getStatCount());
            } else if (s.getClockStatus() != null && ClockStatus.STAYOUT_LATE.getType() == s.getClockStatus()) {
              dto.setStayoutLateDays(s.getStatCount());
            }
          }
          //周晚归和周未归次数大于0才显示出来
          if (dto.getStayoutDays() > 0 || dto.getStayoutLateDays() > 0) {
            analysisExceptionClockByWeekRsqDTOS.add(dto);
          }
        }
      }
    }
    //排序字段 周未归次数：stayoutDays 周晚归次数：stayoutLateDays
    if ("stayoutDays".equals(orderBy)) {
      analysisExceptionClockByWeekRsqDTOS.sort(new AnalysisExceptionClockByWeekCompatator01());
    } else if ("stayoutLateDays".equals(orderBy)) {
      analysisExceptionClockByWeekRsqDTOS.sort(new AnalysisExceptionClockByWeekCompatator02());
    }

    //desc降序，asc升序
    if ("desc".equals(descOrAsc)) {
      Collections.reverse(analysisExceptionClockByWeekRsqDTOS);
    }

    PageInfo pageInfo = ListUtil.getPagingResultMap(analysisExceptionClockByWeekRsqDTOS, pageNo, pageSize);

    List<Long> userIds = getUserIds(analysisExceptionClockByWeekRsqDTOS);
    List<User> userList = userService.selectByPrimaryKeyList(userIds);
    Map<Long, User> userMap = ConvertUtil.getUserMap(userList);
    List<UserClass> userClassList = userClassService.listByUserIds(userIds);
    Map<Long, Long> userClassMap = ConvertUtil.getUserClassMap(userClassList);
    Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();
    Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
    Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();
    Map<Long, DormitoryInfo> dormitoryInfoMap = dormitoryCacheService.getDormitoryMap();

    List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByUserIds(userIds);
    Map<Long, DormitoryUser> userDormitoryRefMap = ConvertUtil.getUserDormitoryRefMap(dormitoryUserList);

    Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();
    
    for (AnalysisExceptionClockByWeekRsqDTO s : analysisExceptionClockByWeekRsqDTOS) {
      User user = userMap.get(s.getStudentId());
      if (user != null) {
        s.setProfilePhoto(user.getHeadPortraitPath());
        s.setStudentName(user.getUserName());
        s.setStudentCode(user.getCode());
      }
      Long classId = userClassMap.get(s.getStudentId());
      s.setClassId(classId);
      ClassInfo classInfo = classInfoMap.get(classId);
      if (classInfo != null) {
        s.setClassName(classInfo.getClassCode());
        s.setMajorId(classInfo.getMajorId());
        MajorInfo majorInfo = majorInfoMap.get(classInfo.getMajorId());
        if (majorInfo != null) {
          s.setMajorName(majorInfo.getName());
          s.setCollegeId(majorInfo.getOrgId());
          CollegeInfo collegeInfo = collegeInfoMap.get(majorInfo.getOrgId());
          if (collegeInfo != null) {
            s.setCollegeName(collegeInfo.getName());
          }
        }
      }
      DormitoryUser dormitoryUser = userDormitoryRefMap.get(s.getStudentId());
      if (dormitoryUser != null) {
        s.setBedCode(dormitoryUser.getBedCode());
        s.setDormitoryId(dormitoryUser.getDormitoryId());
        DormitoryInfo dormitoryInfo = dormitoryInfoMap.get(dormitoryUser.getDormitoryId());
        if (dormitoryInfo != null) {
          s.setBuildingId(dormitoryInfo.getBuildingId());
          BuildingInfo buildingInfo = buildingInfoMap.get(dormitoryInfo.getBuildingId());
          if (buildingInfo != null) {
            s.setBuildingName(buildingInfo.getName());
          }
        }
      }
    }

    //3.组装返回结果
    Page<AnalysisExceptionClockByWeekRsqDTO> analysisExceptionClockByWeekRsqDTOPage = new Page<>();
    analysisExceptionClockByWeekRsqDTOPage.setResult(pageInfo.getList());
    analysisExceptionClockByWeekRsqDTOPage.setTotalCount((int) pageInfo.getTotal());
    analysisExceptionClockByWeekRsqDTOPage.setPageSize(pageSize);
    analysisExceptionClockByWeekRsqDTOPage.setPageNo(pageNo);
    analysisExceptionClockByWeekRsqDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(analysisExceptionClockByWeekRsqDTOPage);

  }

}
