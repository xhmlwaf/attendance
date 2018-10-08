package com.yunhuakeji.attendance.biz.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.aspect.RequestLog;
import com.yunhuakeji.attendance.biz.CommonBiz;
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
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByWeekRsqDTO;
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
import com.yunhuakeji.attendance.util.ListUtil;

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

    Long clockDate = ConvertUtil.getRealTimeStatDay(clockSetting);
    if (clockDate == null) {
      return Result.success(clockStatByBuildingRspDTOList);
    }

    List<BuildingClockStatDO> buildingClockStatDOList = studentClockService.statByBuilding(clockDate);
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

    return Result.success(clockStatByBuildingRspDTOList);
  }

  @Override
  public PagedResult<ClockStatByStudentRspDTO> realTimeStatByStudent(Long buildingId, Integer pageNo, Integer pageSize) {

    List<StudentDormitoryBuildingDO> studentDormitoryBuildingDOList =
        studentInfoService.listStudentOrderByBuilding(buildingId);

    List studentIds = ConvertUtil.getStudentIdsByStudetnDormitoryBuilding(studentDormitoryBuildingDOList);

    if (CollectionUtils.isEmpty(studentDormitoryBuildingDOList)) {
      return returnForNull(pageNo, pageSize);
    }

    // 判断是否打卡
    ClockSetting clockSetting = clockSettingService.getClockSetting();
    Long clockDate = ConvertUtil.getRealTimeStatDay(clockSetting);
    logger.info("clockDate:{}", clockDate);
    if (clockDate == null) {
      return returnForNull(pageNo, pageSize);
    }

    List<StudentClock> studentClockList = studentClockService.list(studentIds, clockDate);
    logger.info("studentClockList.size:{}", studentClockList.size());
    Map<Long, StudentClock> resultMap = ConvertUtil.getStudentIdClockMap(studentClockList);

    List<ClockStatByStudentRspDTO> clockStatByStudentRspDTOList = new ArrayList<>();

    for (StudentDormitoryBuildingDO dto : studentDormitoryBuildingDOList) {
      StudentClock studentClock = resultMap.get(dto.getStudentId());
      ClockStatByStudentRspDTO ddd = new ClockStatByStudentRspDTO();
      if (studentClock == null) {
        ddd.setColckStatus(ClockStatus.NOT_CLOCK.getType());
      } else {
        ddd.setColckStatus(studentClock.getClockStatus());
      }
      ddd.setStudentId(dto.getStudentId());
      ddd.setBuildingId(ddd.getBuildingId());
      ddd.setDormitoryId(ddd.getDormitoryId());
      clockStatByStudentRspDTOList.add(ddd);
    }


    PageInfo<ClockStatByStudentRspDTO> pageInfo = ListUtil.getPagingResultMap(clockStatByStudentRspDTOList, pageNo, pageSize);
    List<Long> targetStudentIds = pageInfo.getList().stream().map(e -> e.getStudentId()).collect(Collectors.toList());

    List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByUserIds(targetStudentIds);
    Map<Long, DormitoryUser> userToDormitoryMap = ConvertUtil.getUserToDormitoryMap(dormitoryUserList);
    Map<Long, DormitoryInfo> dormitoryInfoMap = dormitoryCacheService.getDormitoryMap();
    Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();
    List<UserClass> userClassList = userClassService.listByUserIds(targetStudentIds);
    Map<Long, Long> userClassMap = ConvertUtil.getUserClassMap(userClassList);
    Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();
    Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
    Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();

    List<User> userList = userService.selectByPrimaryKeyList(targetStudentIds);
    Map<Long, User> userMap = ConvertUtil.getUserMap(userList);

    List<Long> instructorIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(pageInfo.getList())) {
      for (ClockStatByStudentRspDTO dto : pageInfo.getList()) {
        long studentId = dto.getStudentId();
        User user = userMap.get(studentId);
        if (user != null) {
          dto.setStudentName(user.getUserName());
          dto.setStudentCode(user.getCode());
        }
        DormitoryUser dormitoryUser = userToDormitoryMap.get(studentId);
        if (dormitoryUser != null) {
          dto.setDormitoryId(dormitoryUser.getDormitoryId());
          dto.setBedCode(dormitoryUser.getBedCode());
        }
        DormitoryInfo dormitoryInfo = dormitoryInfoMap.get(dto.getDormitoryId());
        CommonBiz.setDormitoryAndBuilding(buildingInfoMap, dto, dormitoryInfo);
        dto.setClassId(userClassMap.get(studentId));
        ClassInfo classInfo = classInfoMap.get(userClassMap.get(studentId));
        CommonBiz.setMajorAndCollegeInfo(instructorIds, majorInfoMap, collegeInfoMap, dto, classInfo);
      }


      if (!CollectionUtils.isEmpty(instructorIds) && !CollectionUtils.isEmpty(clockStatByStudentRspDTOList)) {
        List<User> instructorList = userService.selectByPrimaryKeyList(instructorIds);
        Map<Long, User> instructorMap = ConvertUtil.getUserMap(instructorList);
        for (ClockStatByStudentRspDTO dto : pageInfo.getList()) {
          User user = instructorMap.get(dto.getInstructorId());
          if (user != null) {
            dto.setInstructorName(user.getUserName());
          }
        }
      }
    }

    int totalCount = pageInfo != null ? (int) pageInfo.getTotal() : 0;
    int pages = pageInfo != null ? pageInfo.getPages() : 0;
    //3.组装返回结果
    Page<ClockStatByStudentRspDTO> clockStatByStudentRspDTOPage = new Page<>();
    clockStatByStudentRspDTOPage.setResult(pageInfo.getList());
    clockStatByStudentRspDTOPage.setTotalCount(totalCount);
    clockStatByStudentRspDTOPage.setPageSize(pageSize);
    clockStatByStudentRspDTOPage.setPageNo(pageNo);
    clockStatByStudentRspDTOPage.setTotalPages(pages);

    return PagedResult.success(clockStatByStudentRspDTOPage);
  }

  private PagedResult<ClockStatByStudentRspDTO> returnForNull(Integer pageNo, Integer pageSize) {
    Page<ClockStatByStudentRspDTO> clockStatByStudentRspDTOPage = new Page<>();
    clockStatByStudentRspDTOPage.setPageNo(pageNo);
    clockStatByStudentRspDTOPage.setPageSize(pageSize);
    clockStatByStudentRspDTOPage.setTotalCount(0);
    clockStatByStudentRspDTOPage.setTotalPages(0);
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
