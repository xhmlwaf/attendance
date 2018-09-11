package com.yunhuakeji.attendance.biz.impl;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.aspect.RequestLog;
import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.RealTimeStatBiz;
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
import com.yunhuakeji.attendance.dao.basedao.model.StudentDormitoryBuildingDO;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.dao.bizdao.model.BuildingClockStatDO;
import com.yunhuakeji.attendance.dao.bizdao.model.BuildingStudentStatDO;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockSetting;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.dto.response.ClockStatByBuildingRspDTO;
import com.yunhuakeji.attendance.dto.response.ClockStatByStudentRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryAdminQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.StudentBaseInfoDTO;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.service.baseservice.DormitoryUserService;
import com.yunhuakeji.attendance.service.baseservice.StudentInfoService;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.ClockSettingService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RealTimeStatBizImpl implements RealTimeStatBiz {

  private static final Logger logger = LoggerFactory.getLogger(RealTimeStatBizImpl.class);

  @Autowired
  private BuildingCacheService buildingCacheService;

  @Autowired
  private DormitoryCacheService dormitoryCacheService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private MajorCacheService majorCacheService;

  @Autowired
  private StudentClockService studentClockService;

  @Autowired
  private ClockSettingService clockSettingService;

  @Autowired
  private StudentInfoService studentInfoService;

  @Autowired
  private UserClassService userClassService;

  @Autowired
  private OrgCacheService orgCacheService;

  @Autowired
  private DormitoryUserService dormitoryUserService;

  @Autowired
  private UserService userService;

  @Override
  public Result<List<ClockStatByBuildingRspDTO>> realTimeStatByBuilding() {

    List<BuildingInfo> buildingInfoList = buildingCacheService.list();
    List<ClockStatByBuildingRspDTO> clockStatByBuildingRspDTOList = new ArrayList<>();
    if (CollectionUtils.isEmpty(buildingInfoList)) {
      return Result.success(new ArrayList<>());
    }

    for (BuildingInfo buildingInfo : buildingInfoList) {
      ClockStatByBuildingRspDTO dto = new ClockStatByBuildingRspDTO();
      dto.setBuildingId(buildingInfo.getBuildingId());
      dto.setBuildingName(buildingInfo.getName());
      clockStatByBuildingRspDTOList.add(dto);
    }

    ClockSetting clockSetting = clockSettingService.getClockSetting();
    //打卡开始时间
    long clockStartTime = clockSetting.getClockStartTime();
    //查寝结束时间
    long checkDormEndTime = clockSetting.getCheckDormEndTime();
    long currTime = DateUtil.currHhmmssToLong();
    logger.info("currTime:{},clockStartTime:{}", currTime, clockStartTime);
    if (currTime >= clockStartTime || currTime <= checkDormEndTime) {
      long currDate = 0;
      if (currTime >= clockStartTime) {
        currDate = DateUtil.currYYYYMMddToLong();
      } else {
        currDate = DateUtil.getYearMonthDayByDate(DateUtil.nowDateAdd(-1));
      }

      List<BuildingClockStatDO> buildingClockStatDOList = studentClockService.statByBuilding(currDate);
      List<Long> buildingIds = ConvertUtil.getBuildingIds(buildingInfoList);
      List<BuildingStudentStatDO> buildingStudentStatDOList = studentInfoService.statBuildingStudent(buildingIds);
      Map<Long, Integer> buildingClockMap = getBuildingClockStatMap(buildingClockStatDOList);
      Map<Long, Integer> buildingStudentMap = getBuildingStudentStatMap(buildingStudentStatDOList);
      for (ClockStatByBuildingRspDTO item : clockStatByBuildingRspDTOList) {
        Integer clock = buildingClockMap.get(item.getBuildingId());
        item.setClockCount(clock != null ? clock : 0);
        Integer student = buildingStudentMap.get(item.getBuildingId());
        item.setNotClockCount(student != null ? student - item.getClockCount() : 0);
      }
    }

    return Result.success(clockStatByBuildingRspDTOList);
  }

  @Override
  public PagedResult<ClockStatByStudentRspDTO> realTimeStatByStudent(Integer pageNo, Integer pageSize) {

    List<ClockStatByStudentRspDTO> clockStatByStudentRspDTOList = new ArrayList<>();
    PageInfo<StudentDormitoryBuildingDO> pageInfo =
        studentInfoService.listStudentOrderByBuilding(pageNo, pageSize);
    List<StudentDormitoryBuildingDO> studentDormitoryBuildingDOList = pageInfo.getList();
    List studentIds = ConvertUtil.getStudentIdsByStudetnDormitoryBuilding(studentDormitoryBuildingDOList);

    if (!CollectionUtils.isEmpty(studentDormitoryBuildingDOList)) {

      List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByUserIds(studentIds);
      Map<Long, DormitoryUser> userToDormitoryMap = ConvertUtil.getUserToDormitoryMap(dormitoryUserList);
      Map<Long, DormitoryInfo> dormitoryInfoMap = dormitoryCacheService.getDormitoryMap();
      Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();
      List<UserClass> userClassList = userClassService.listByUserIds(studentIds);
      Map<Long, Long> userClassMap = ConvertUtil.getUserClassMap(userClassList);
      Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();
      Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
      Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();

      List<User> userList = userService.selectByPrimaryKeyList(studentIds);
      Map<Long, User> userMap = ConvertUtil.getUserMap(userList);

      List<Long> instructorIds = new ArrayList<>();
      for (StudentDormitoryBuildingDO s : studentDormitoryBuildingDOList) {
        ClockStatByStudentRspDTO dto = new ClockStatByStudentRspDTO();
        dto.setStudentId(s.getStudentId());
        dto.setBuildingId(s.getBuildingId());
        dto.setDormitoryId(s.getDormitoryId());

        User user = userMap.get(s.getStudentId());
        if (user != null) {
          dto.setStudentName(user.getUserName());
          dto.setStudentCode(user.getCode());
        }
        DormitoryUser dormitoryUser = userToDormitoryMap.get(s.getStudentId());
        if (dormitoryUser != null) {
          dto.setBedCode(dormitoryUser.getBedCode());
        }
        DormitoryInfo dormitoryInfo = dormitoryInfoMap.get(s.getDormitoryId());
        if (dormitoryInfo != null) {
          dto.setDormitoryName(dormitoryInfo.getName());
          dto.setBuildingId(dormitoryInfo.getBuildingId());
          BuildingInfo buildingInfo = buildingInfoMap.get(dormitoryInfo.getBuildingId());
          if (buildingInfo != null) {
            dto.setBuildingName(buildingInfo.getName());
          }
        }
        dto.setClassId(userClassMap.get(s.getStudentId()));
        ClassInfo classInfo = classInfoMap.get(userClassMap.get(s.getStudentId()));
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
        clockStatByStudentRspDTOList.add(dto);
      }

      if (!CollectionUtils.isEmpty(instructorIds) && !CollectionUtils.isEmpty(clockStatByStudentRspDTOList)) {
        List<User> instructorList = userService.selectByPrimaryKeyList(instructorIds);
        Map<Long, User> instructorMap = ConvertUtil.getUserMap(instructorList);
        for (ClockStatByStudentRspDTO dto : clockStatByStudentRspDTOList) {
          User user = instructorMap.get(dto.getInstructorId());
          if (user != null) {
            dto.setInstructorName(user.getUserName());
          }
        }
      }

      if (!CollectionUtils.isEmpty(clockStatByStudentRspDTOList)) {
        ClockSetting clockSetting = clockSettingService.getClockSetting();
        //打卡开始时间
        long clockStartTime = clockSetting.getClockStartTime();
        //查寝结束时间
        long checkDormEndTime = clockSetting.getCheckDormEndTime();
        long currTime = DateUtil.currHhmmssToLong();
        logger.info("currTime:{},clockStartTime:{}", currTime, clockStartTime);

        if (currTime >= clockStartTime || currTime <= checkDormEndTime) {
          long currDate = 0;
          if (currTime >= clockStartTime) {
            currDate = DateUtil.currYYYYMMddToLong();
          } else {
            currDate = DateUtil.getYearMonthDayByDate(DateUtil.nowDateAdd(-1));
          }

          List<StudentClock> studentClockList = studentClockService.list(studentIds, currDate);
          Map<Long, StudentClock> resultMap = ConvertUtil.getStudentClockMap(studentClockList);
          ConvertUtil.getStudentIds(studentClockList);
          for (ClockStatByStudentRspDTO dto : clockStatByStudentRspDTOList) {

            StudentClock studentClock = resultMap.get(dto.getStudentId());
            if (studentClock == null) {
              dto.setColckStatus(ClockStatus.NOT_CLOCK.getType());
            } else {
              dto.setColckStatus(studentClock.getClockStatus());
            }
          }

        }
      }

    }

    //3.组装返回结果
    Page<ClockStatByStudentRspDTO> clockStatByStudentRspDTOPage = new Page<>();
    clockStatByStudentRspDTOPage.setResult(clockStatByStudentRspDTOList);
    clockStatByStudentRspDTOPage.setTotalCount((int) pageInfo.getTotal());
    clockStatByStudentRspDTOPage.setPageSize(pageSize);
    clockStatByStudentRspDTOPage.setPageNo(pageNo);
    clockStatByStudentRspDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(clockStatByStudentRspDTOPage);
  }


  /**
   * key buildingId value打卡人数
   *
   * @param buildingClockStatDOList :
   * @return : java.util.Map<java.lang.Long,java.lang.Integer>
   */
  private Map<Long, Integer> getBuildingClockStatMap(List<BuildingClockStatDO> buildingClockStatDOList) {
    if (!CollectionUtils.isEmpty(buildingClockStatDOList)) {
      return buildingClockStatDOList.stream().collect(Collectors.toMap(BuildingClockStatDO::getBuildingId, BuildingClockStatDO::getClockStatCount, (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  /**
   * key buildingId value应打卡人数
   *
   * @param buildingStudentStatDOList :
   * @return : java.util.Map<java.lang.Long,java.lang.Integer>
   */
  private Map<Long, Integer> getBuildingStudentStatMap(List<BuildingStudentStatDO> buildingStudentStatDOList) {
    if (!CollectionUtils.isEmpty(buildingStudentStatDOList)) {
      return buildingStudentStatDOList.stream().collect(Collectors.toMap(BuildingStudentStatDO::getBuildingId, BuildingStudentStatDO::getStudentTotalCount, (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

}
