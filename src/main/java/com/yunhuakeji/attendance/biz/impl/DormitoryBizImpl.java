package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.aspect.RequestLog;
import com.yunhuakeji.attendance.biz.DormitoryBiz;
import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryUser;
import com.yunhuakeji.attendance.dao.bizdao.model.*;
import com.yunhuakeji.attendance.dto.request.DormitoryCheckOverReqDTO;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckDayStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckWeekStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockDetailStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitorySimpleRspDTO;
import com.yunhuakeji.attendance.enums.AppType;
import com.yunhuakeji.attendance.enums.RoleType;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.baseservice.BuildingInfoService;
import com.yunhuakeji.attendance.service.baseservice.DormitoryInfoService;

import com.yunhuakeji.attendance.service.baseservice.DormitoryUserService;
import com.yunhuakeji.attendance.service.baseservice.StudentInfoService;
import com.yunhuakeji.attendance.service.bizservice.AccountService;
import com.yunhuakeji.attendance.service.bizservice.CheckDormitoryService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.UserBuildingService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.management.relation.Role;
import java.util.*;

@Service
public class DormitoryBizImpl implements DormitoryBiz {

  private static final Logger logger = LoggerFactory.getLogger(DormitoryBizImpl.class);


  @Autowired
  private BuildingInfoService buildingInfoService;

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


  @Override
  public Result<List<BuildingQueryRspDTO>> listAllBuilding() {
    List<BuildingInfo> buildingInfoList = buildingInfoService.listAll();
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

  private byte getRoleTypeByUserId(Long userId){
    Account account = accountService.getAccountByUserId(userId);
    if(account==null){
       return -1;
    }
    return account.getRoleType();
  }


  @Override
  public Result<List<BuildingQueryRspDTO>> listBuildingForApp(Long userId) {

    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();

    if (instructorIds!=null&&instructorIds.contains(userId)) {
      List<DormitoryInfo> dormitoryInfoList = dormitoryInfoService.listDormitoryByInstructorId(userId);
      Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();
      List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
      if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
        for (DormitoryInfo dormitoryInfo : dormitoryInfoList) {
          BuildingQueryRspDTO dto = new BuildingQueryRspDTO();
          dto.setBuildingId(dormitoryInfo.getBuildingId());
          if (buildingInfoMap.get(dormitoryInfo.getBuildingId()) != null) {
            dto.setBuildingName(buildingInfoMap.get(dormitoryInfo.getBuildingId()).getName());
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
          }
        }
      }
      return Result.success(rspDTOList);

    } else if (RoleType.StudentsAffairsAdmin.getType() == roleType) {
      List<BuildingInfo> buildingInfoList = buildingInfoService.listAll();
      List<BuildingQueryRspDTO> rspDTOList = new ArrayList<>();
      if (!CollectionUtils.isEmpty(buildingInfoList)) {
        for (BuildingInfo buildingInfo : buildingInfoList) {
          rspDTOList.add(convertToDormitoryBuildingQueryRspDTO(buildingInfo));
        }
      }
      return Result.success(rspDTOList);
    }
    return Result.success(new ArrayList<>());
  }

  @Override
  public Result<List<DormitorySimpleRspDTO>> listDormitoryForApp(Long userId,Long buildingId, Integer floorNumber) {
    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();
    if (instructorIds!=null&&instructorIds.contains(userId)) {
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
  public Result<List<DormitoryClockStatDTO>> listDormitoryClockStatForApp(Long userId, Long buildingId, Integer floorNumber, Long dormitoryId) {

    List<DormitoryInfo> dormitoryInfoList = getDormitoryInfo(userId, buildingId, floorNumber, dormitoryId);
    if (CollectionUtils.isEmpty(dormitoryInfoList)) {
      return Result.success(new ArrayList<>());
    }
    List<Long> dormitoryIds = getDormitoryIds(dormitoryInfoList);
    List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByDormitoryIds(dormitoryIds);
    List<CheckDormitory> checkDormitoryList = checkDormitoryService.list(dormitoryIds, 1L);//TODO 日期
    Map<Long, List<Long>> dormitoryToUserIdsMap = getDormitoryToUserIdsMap(dormitoryUserList);
    List<Long> studentIds = getStudentIds(dormitoryUserList);
    List<StudentClock> studentClockList = studentClockService.list(studentIds, 1L); //TODO 日期确定 分组查，考虑学生数量过多
    Map<Long, StudentClock> studentIdToStatusMap = getStudentIdToStatusMap(studentClockList);
    Set<Long> sormitoryIdSet = getDormitoryIdSet(checkDormitoryList);
    List<DormitoryClockStatDTO> dormitoryClockStatDTOList = new ArrayList<>();
    for (DormitoryInfo dormitoryInfo : dormitoryInfoList) {
      DormitoryClockStatDTO dto = new DormitoryClockStatDTO();
      dto.setBuildingId(dormitoryInfo.getBuildingId());
      //TODO dto.setBuildingName();
      dto.setDormitoryId(dormitoryInfo.getDormitoryId());
      dto.setDormitoryName(dormitoryInfo.getName());
      dormitoryClockStatDTOList.add(dto);
      // TODO 其他的統計工作
    }
    return Result.success(dormitoryClockStatDTOList);
  }

  @Override
  public Result<DormitoryClockDetailStatDTO> getDormitoryClockDetailStatForApp(Long userId,Long dormitoryId) {
    //TODO 参考上面的
    List<Long> dormitoryIds = new ArrayList<>();
    dormitoryIds.add(dormitoryId);
    List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByDormitoryIds(dormitoryIds);
    List<CheckDormitory> checkDormitoryList = checkDormitoryService.list(dormitoryIds, 1L);//TODO 日期
    Map<Long, List<Long>> dormitoryToUserIdsMap = getDormitoryToUserIdsMap(dormitoryUserList);
    List<Long> studentIds = getStudentIds(dormitoryUserList);
    List<StudentClock> studentClockList = studentClockService.list(studentIds, 1L); //TODO 日期确定 分组查，考虑学生数量过多
    Map<Long, StudentClock> studentIdToStatusMap = getStudentIdToStatusMap(studentClockList);
    Set<Long> sormitoryIdSet = getDormitoryIdSet(checkDormitoryList);

    DormitoryClockDetailStatDTO dto = new DormitoryClockDetailStatDTO();
    //dto.setBuildingId();



    return null;
  }


  @Override
  public Result<DormitoryCheckDayStatRspDTO> dayStat(Long userId, Integer year, Integer month, Integer day) {
    int totalCount = 0;
    Map<String, Object> queryMap = new HashMap<>();
    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();

    queryMap.put("clockDate", DateUtil.ymdToint(year, month, day));
    if (instructorIds!=null&&instructorIds.contains(userId)) {
      //总人数
      totalCount = studentInfoService.countClockStudentByInstructorId(userId);
      queryMap.put("instructorId",userId);

    } else if (RoleType.DormitoryAdmin.getType() == roleType) {
      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
      List<Long> buildingIds = getBuildingIds(userBuildingRefList);
      totalCount = studentInfoService.countClockStudentByBuildingIds(buildingIds);
      queryMap.put("buildingIds",buildingIds);

    } else if (RoleType.StudentsAffairsAdmin.getType() == roleType) {
      totalCount = studentInfoService.countAllClockStudent();
    }
    List<ClockStatByStatusDO> clockStatByStatusDOList = studentClockService.statByStatus(queryMap);
    //TODO 转换等工作

    return null;
  }

  @Override
  public Result<DormitoryCheckWeekStatRspDTO> weekStat(Long userId, Integer weekNumber) {

    byte roleType = getRoleTypeByUserId(userId);
    List<Long> instructorIds = classCacheService.getInstructorIds();

    int totalCount = 0;
    Map<String, Object> queryMap = new HashMap<>();
    queryMap.put("startClockDate", null); //TODO 要算
    queryMap.put("endClockDate", null); //TODO 要算
    if (instructorIds!=null&&instructorIds.contains(userId)) {
      //总人数
      totalCount = studentInfoService.countClockStudentByInstructorId(userId);
      queryMap.put("instructorId",userId);

    } else if (RoleType.DormitoryAdmin.getType() == roleType) {
      List<UserBuildingRef> userBuildingRefList = userBuildingService.listByUserId(userId);
      List<Long> buildingIds = getBuildingIds(userBuildingRefList);
      totalCount = studentInfoService.countClockStudentByBuildingIds(buildingIds);
      queryMap.put("buildingIds",buildingIds);

    } else if (RoleType.StudentsAffairsAdmin.getType() == roleType) {
      totalCount = studentInfoService.countAllClockStudent();
    }
    List<ClockStatByStatusDO> clockStatByStatusDOList = studentClockService.statByStatus(queryMap);
    //TODO 转换等工作

    return null;
  }

  @Override
  public Result addDormitoryCheck(DormitoryCheckOverReqDTO reqDTO) {
    CheckDormitory checkDormitory = new CheckDormitory();
    checkDormitory.setDormitoryId(reqDTO.getDormitoryId());
    checkDormitory.setOperatorName(reqDTO.getOperatorName());
    checkDormitory.setOperateDate(new Date());
    checkDormitory.setStatDate(DateUtil.currHhmmssToLong());
    checkDormitoryService.insert(checkDormitory);
    return Result.success();
  }

  private List<Long> getBuildingIds(List<UserBuildingRef> userBuildingRefList) {
    List<Long> buildingIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(userBuildingRefList)) {
      for (UserBuildingRef userBuildingRef : userBuildingRefList) {
        buildingIds.add(userBuildingRef.getBuildingId());
      }
    }
    return buildingIds;
  }

  private Set<Long> getDormitoryIdSet(List<CheckDormitory> checkDormitoryList) {
    Set<Long> sormitoryIdSet = new HashSet<>();
    if (!CollectionUtils.isEmpty(checkDormitoryList)) {
      for (CheckDormitory checkDormitory : checkDormitoryList) {
        sormitoryIdSet.add(checkDormitory.getDormitoryId());
      }
    }
    return sormitoryIdSet;
  }

  private Map<Long, StudentClock> getStudentIdToStatusMap(List<StudentClock> studentClockList) {
    Map<Long, StudentClock> studentIdToStatusMap = new HashMap<>();
    if (CollectionUtils.isEmpty(studentClockList)) {
      for (StudentClock studentClock : studentClockList) {
        studentIdToStatusMap.put(studentClock.getUserId(), studentClock);
      }
    }
    return studentIdToStatusMap;
  }

  private List<Long> getStudentIds(List<DormitoryUser> dormitoryUserList) {
    List<Long> studentIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      for (DormitoryUser dormitoryUser : dormitoryUserList) {
        studentIds.add(dormitoryUser.getUserId());
      }
    }
    return studentIds;
  }

  private Map<Long, List<Long>> getDormitoryToUserIdsMap(List<DormitoryUser> dormitoryUserList) {
    Map<Long, List<Long>> dormitoryToUserIdsMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(dormitoryUserList)) {
      for (DormitoryUser dormitoryUser : dormitoryUserList) {
        List<Long> userIds = dormitoryToUserIdsMap.get(dormitoryUser.getDormitoryId());
        if (userIds == null) {
          userIds = new ArrayList<>();
        }
        userIds.add(dormitoryUser.getUserId());
        dormitoryToUserIdsMap.put(dormitoryUser.getDormitoryId(), userIds);
      }
    }
    return dormitoryToUserIdsMap;
  }

  /**
   * 从dormitoryInfoList获取DormitoryIds
   *
   * @param dormitoryInfoList :
   * @return : java.util.List<java.lang.Long>
   */
  private List<Long> getDormitoryIds(List<DormitoryInfo> dormitoryInfoList) {
    List<Long> dormitoryIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
      for (DormitoryInfo dormitoryInfo : dormitoryInfoList) {
        dormitoryIds.add(dormitoryInfo.getDormitoryId());
      }
    }
    return dormitoryIds;
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
    if (instructorIds!=null&&instructorIds.contains(userId)) {
      dormitoryInfoList = dormitoryInfoService.listDormitoryByInstructorId(userId);
      if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
        Iterator<DormitoryInfo> dormitoryInfoIterable = dormitoryInfoList.iterator();
        while (dormitoryInfoIterable.hasNext()) {
          DormitoryInfo dormitoryInfo = dormitoryInfoIterable.next();
          if (!dormitoryInfo.getBuildingId().equals(buildingId)) {
            dormitoryInfoIterable.remove();
          }
          if (floorNumber != null && dormitoryInfo.getFloorNumber().intValue() != floorNumber) {
            dormitoryInfoIterable.remove();
          }
        }
      }
    } else if (RoleType.DormitoryAdmin.getType() == roleType || RoleType.StudentsAffairsAdmin.getType() == roleType) {
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

}
