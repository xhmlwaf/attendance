package com.yunhuakeji.attendance.biz.impl;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.biz.CareBiz;
import com.yunhuakeji.attendance.biz.CommonBiz;
import com.yunhuakeji.attendance.biz.CommonHandlerUtil;
import com.yunhuakeji.attendance.biz.CommonQueryUtil;
import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.DormitoryCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.comparator.ClockDaySettingCompatator01;
import com.yunhuakeji.attendance.comparator.StudentClockStatusCompatator01;
import com.yunhuakeji.attendance.comparator.StudentClockStatusCompatator02;
import com.yunhuakeji.attendance.comparator.StudentClockStatusCompatator03;
import com.yunhuakeji.attendance.constants.ErrorCode;
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
import com.yunhuakeji.attendance.dao.bizdao.model.Care;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentCareCountStatDO;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockStatusDO;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.dto.request.CareUpdateReqDTO;
import com.yunhuakeji.attendance.dto.request.DeleteCareReqDTO;
import com.yunhuakeji.attendance.dto.request.StartCareReqDTO;
import com.yunhuakeji.attendance.dto.response.CanStartCareRspDTO;
import com.yunhuakeji.attendance.dto.response.CareTaskBaseInfoDTO;
import com.yunhuakeji.attendance.dto.response.StudentCareRspDTO;
import com.yunhuakeji.attendance.enums.CareStatus;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.baseservice.DormitoryUserService;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.CareService;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.UserOrgRefService;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.ListUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CareBizImpl implements CareBiz {

  @Autowired
  private CareService careService;

  @Autowired
  private UserClassService userClassService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private OrgCacheService orgCacheService;

  @Autowired
  private MajorCacheService majorCacheService;

  @Autowired
  private UserService userService;

  @Autowired
  private DormitoryUserService dormitoryUserService;

  @Autowired
  private DormitoryCacheService dormitoryCacheService;

  @Autowired
  private BuildingCacheService buildingCacheService;

  @Autowired
  private ClockDaySettingService clockDaySettingService;

  @Autowired
  private StudentClockService studentClockService;

  @Autowired
  private UserOrgRefService userOrgRefService;


  private List<Long> getOrgIds(Long userId) {
    List<UserOrgRef> userOrgRefList = userOrgRefService.listByUserId(userId);
    if (CollectionUtils.isEmpty(userOrgRefList)) {
      List<CollegeInfo> collegeInfoList = orgCacheService.list();
      return collegeInfoList.stream().map(CollegeInfo::getOrgId).collect(Collectors.toList());
    }
    return userOrgRefList.stream().map(UserOrgRef::getOrgId).collect(Collectors.toList());
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
  public PagedResult<CareTaskBaseInfoDTO> listByInstructor(Long instructorId, Byte careStatus,
      Integer pageNo, Integer pageSize) {

    PageInfo pageInfo = careService.pageByInstructor(instructorId, careStatus, pageNo, pageSize);
    List<Care> careList = pageInfo.getList();

    //4.组装结果并返回
    Page<CareTaskBaseInfoDTO> careTaskBaseInfoDTOPage = new Page<>();
    careTaskBaseInfoDTOPage.setPageNo(pageNo);
    careTaskBaseInfoDTOPage.setTotalPages(pageInfo.getPages());
    careTaskBaseInfoDTOPage.setPageSize(pageSize);
    careTaskBaseInfoDTOPage.setTotalCount((int) pageInfo.getTotal());

    List<CareTaskBaseInfoDTO> careTaskBaseInfoDTOList = new ArrayList<>();
    List<Long> userIds = ConvertUtil.getUserIdsByCareList(pageInfo.getList());

    List<User> userList = userService.selectByPrimaryKeyList(userIds);
    Map<Long, User> userMap = ConvertUtil.getUserMap(userList);

    List<UserClass> userClassList = userClassService.listByUserIds(userIds);
    Map<Long, Long> userClassMap = ConvertUtil.getUserClassMap(userClassList);
    Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();

    if (!CollectionUtils.isEmpty(pageInfo.getList())) {
      getCareTaskList(careList, careTaskBaseInfoDTOList, userMap, userClassMap, classInfoMap);
    }
    careTaskBaseInfoDTOPage.setResult(careTaskBaseInfoDTOList);
    return PagedResult.success(careTaskBaseInfoDTOPage);
  }

  private void getCareTaskList(List<Care> careList,
      List<CareTaskBaseInfoDTO> careTaskBaseInfoDTOList, Map<Long, User> userMap,
      Map<Long, Long> userClassMap, Map<Long, ClassInfo> classInfoMap) {
    for (Care care : careList) {
      CareTaskBaseInfoDTO dto = new CareTaskBaseInfoDTO();
      dto.setCareId(care.getId());
      dto.setDealDate(care.getDealTime());
      dto.setInstructorId(care.getInstructorId());
      dto.setStudentId(care.getStudentId());
      User user = userMap.get(care.getStudentId());
      if (user != null) {
        dto.setStudentName(user.getUserName());
        dto.setStudentCode(user.getCode());
        dto.setProfilePhoto(user.getHeadPortraitPath());
      }
      Long classId = userClassMap.get(care.getStudentId());
      dto.setClassId(classId);
      ClassInfo classInfo = classInfoMap.get(classId);
      if (classInfo != null) {
        dto.setClassName(classInfo.getClassCode());
      }
      dto.setRemark(care.getRemark());
      dto.setStatus(care.getCareStatus());
      dto.setTaskDate(care.getOriginateTime());
      careTaskBaseInfoDTOList.add(dto);
    }
  }


  @Override
  public Result updateCare(CareUpdateReqDTO reqDTO) {
    Care care = new Care();
    care.setId(reqDTO.getCareId());
    care.setCareStatus(CareStatus.YES.getType());
    care.setRemark(reqDTO.getRemark());
    careService.update(care);
    return Result.success(null);
  }

  @Override
  public PagedResult<CareTaskBaseInfoDTO> listByStudent(Long studentId, Integer pageNo,
      Integer pageSize) {
    PageInfo pageInfo = careService.pageByStudent(studentId, pageNo, pageSize);
    List<Care> careList = pageInfo.getList();
    if (CollectionUtils.isEmpty(careList)) {
      return PagedResult.success(pageNo, pageSize);
    }

    //4.组装结果并返回
    Page<CareTaskBaseInfoDTO> careTaskBaseInfoDTOPage = new Page<>();
    careTaskBaseInfoDTOPage.setPageNo(pageNo);
    careTaskBaseInfoDTOPage.setTotalPages(pageInfo.getPages());
    careTaskBaseInfoDTOPage.setPageSize(pageSize);
    careTaskBaseInfoDTOPage.setTotalCount((int) pageInfo.getTotal());

    List<CareTaskBaseInfoDTO> careTaskBaseInfoDTOList = new ArrayList<>();
    List<Long> userIds = ConvertUtil.getUserIdsByCareList(careList);
    List<User> userList = userService.selectByPrimaryKeyList(userIds);
    Map<Long, User> userMap = ConvertUtil.getUserMap(userList);

    List<UserClass> userClassList = userClassService.listByUserIds(userIds);
    Map<Long, Long> userClassMap = ConvertUtil.getUserClassMap(userClassList);
    Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();

    getCareTaskList(careList, careTaskBaseInfoDTOList, userMap, userClassMap, classInfoMap);

    careTaskBaseInfoDTOPage.setResult(careTaskBaseInfoDTOList);
    return PagedResult.success(careTaskBaseInfoDTOPage);
  }

  @Override
  public Result startCare(StartCareReqDTO startCareReqDTO) {

    List<Long> studentIds = startCareReqDTO.getStudentIds();
    if (CollectionUtils.isEmpty(studentIds)) {
      throw new BusinessException(ErrorCode.PARAMS_ERROR);
    }
    List<Care> cares = careService.listByIdsAndDate(studentIds);
    if (!CollectionUtils.isEmpty(cares)) {
      throw new BusinessException(ErrorCode.START_CARE_ONLY_ONCE_ONEDAY);
    }
    List<Care> careList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(studentIds)) {
      List<UserClass> userClassList = userClassService.listByUserIds(studentIds);
      Map<Long, Long> userClassMap = ConvertUtil.getUserClassMap(userClassList);
      Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();
      Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
      for (Long id : studentIds) {
        Care care = new Care();
        care.setId(DateUtil.uuid());
        care.setStudentId(id);
        care.setOriginatorId(startCareReqDTO.getOperatorId());
        care.setCareStatus(CareStatus.NO.getType());
        care.setOriginateTime(new Date());
        care.setClassId(userClassMap.get(id));
        ClassInfo classInfo = classInfoMap.get(userClassMap.get(id));
        if (classInfo != null) {
          care.setMajorId(classInfo.getMajorId());
          care.setInstructorId(classInfo.getInstructorId());
          MajorInfo majorInfo = majorInfoMap.get(classInfo.getMajorId());
          if (majorInfo != null) {
            care.setOrgId(majorInfo.getOrgId());
          }
        }
        careList.add(care);
      }
      careService.batchInsert(careList);
    }
    return Result.success();
  }

  @Override
  public Result deleteCare(DeleteCareReqDTO deleteCareReqDTO) {
    List<Long> careIds = deleteCareReqDTO.getCareIds();
    List<Care> careList = careService.listByIds(careIds);
    if (!CollectionUtils.isEmpty(careList)) {
      for (Care care : careList) {
        if (CareStatus.YES.getType() == care.getCareStatus()) {
          throw new BusinessException(ErrorCode.CARE_CAN_NOT_DELETE);
        }
      }
      careService.batchDelete(careIds);
    }
    return Result.success();
  }

  @Override
  public PagedResult<StudentCareRspDTO> studentCarePage(Byte careStatus,
      String nameOrCode,
      Long orgId, Long majorId,
      Long instructorId,
      Integer pageNo, Integer pageSize, Long userId) {
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
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
    List<Long> lastClassIds = ConvertUtil
        .getLastClassIds(orgClassIds, majorClassIds, instructorClassIds);

    //根据classId和状态查询学生昨天的状态
    PageInfo<Care> pageInfo = null;
    if (orgId != null || majorId != null || instructorId != null) {
      if (CollectionUtils.isEmpty(lastClassIds)) {
        return PagedResult.success(pageNo, pageSize);
      }
    }

    pageInfo = careService
        .pageByClassIdsAndStatus(lastClassIds, nameOrCode, careStatus, pageNo, pageSize);
    if (CollectionUtils.isEmpty(pageInfo.getList())) {
      return PagedResult.success(pageNo, pageSize);
    }
    List<Long> userIds = ConvertUtil.getUserIdsByCareList(pageInfo.getList());

    List<User> userList = userService.selectByPrimaryKeyList(userIds);
    Map<Long, User> userMap = ConvertUtil.getUserMap(userList);
    Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();

    List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByUserIds(userIds);
    Map<Long, DormitoryUser> userDormitoryRefMap = ConvertUtil
        .getUserDormitoryRefMap(dormitoryUserList);
    Map<Long, DormitoryInfo> dormitoryInfoMap = dormitoryCacheService.getDormitoryMap();
    Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
    Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();
    Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();

    List<StudentCareRspDTO> studentCareRspDTOList = new ArrayList<>();
    List<Long> instructorIds = new ArrayList<>();
    for (Care care : pageInfo.getList()) {
      StudentCareRspDTO dto = new StudentCareRspDTO();
      dto.setTaskCreateTime(care.getOriginateTime());
      dto.setTaskDealTime(care.getDealTime());
      dto.setStudentId(care.getStudentId());
      dto.setClassId(care.getClassId());
      dto.setCareId(care.getId());
      ClassInfo classInfo = classInfoMap.get(care.getClassId());
      if (classInfo != null) {
        dto.setClassName(classInfo.getClassCode());
        dto.setMajorId(classInfo.getMajorId());
        if(classInfo.getInstructorId()!=null){
          instructorIds.add(classInfo.getInstructorId());
        }
        dto.setInstructorId(classInfo.getInstructorId());
        MajorInfo majorInfo = majorInfoMap.get(classInfo.getMajorId());
        setMajorAndCollege(collegeInfoMap, dto, majorInfo);
      }

      User user = userMap.get(care.getStudentId());
      if (user != null) {
        dto.setStudentName(user.getUserName());
        dto.setStudentCode(user.getCode());
        dto.setProfilePhoto(user.getHeadPortraitPath());
      }
      DormitoryUser dormitoryUser = userDormitoryRefMap.get(care.getStudentId());
      if (dormitoryUser != null) {
        dto.setDormitoryId(dormitoryUser.getDormitoryId());
        dto.setBedCode(dormitoryUser.getBedCode());
        DormitoryInfo dormitoryInfo = dormitoryInfoMap.get(dormitoryUser.getDormitoryId());

        if (dormitoryInfo != null) {
          dto.setDormitoryId(dormitoryInfo.getDormitoryId());
          dto.setDormitoryName(dormitoryInfo.getName());
          dto.setBuildingId(dormitoryInfo.getBuildingId());
          BuildingInfo buildingInfo = buildingInfoMap.get(dormitoryInfo.getBuildingId());
          if (buildingInfo != null) {
            dto.setBuildingName(buildingInfo.getName());
          }
        }

      }
      studentCareRspDTOList.add(dto);
    }

    if (!CollectionUtils.isEmpty(instructorIds) && !CollectionUtils
        .isEmpty(studentCareRspDTOList)) {
      List<User> instructorList = userService.selectByPrimaryKeyList(instructorIds);
      Map<Long, User> instructorMap = ConvertUtil.getUserMap(instructorList);
      for (StudentCareRspDTO dto : studentCareRspDTOList) {
        User user = instructorMap.get(dto.getInstructorId());
        if (user != null) {
          dto.setInstructorName(user.getUserName());
        }
      }
    }

    //3.组装返回结果
    Page<StudentCareRspDTO> studentCareRspDTOPage = new Page<>();
    studentCareRspDTOPage.setResult(studentCareRspDTOList);
    studentCareRspDTOPage.setTotalCount((int) pageInfo.getTotal());
    studentCareRspDTOPage.setPageSize(pageSize);
    studentCareRspDTOPage.setPageNo(pageNo);
    studentCareRspDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(studentCareRspDTOPage);
  }

  public void setMajorAndCollege(Map<Long, CollegeInfo> collegeInfoMap, StudentCareRspDTO dto,
      MajorInfo majorInfo) {
    if (majorInfo != null) {
      CollegeInfo collegeInfo = collegeInfoMap.get(majorInfo.getOrgId());
      if (collegeInfo != null) {
        dto.setCollegeName(collegeInfo.getName());
      }
      dto.setCollegeId(majorInfo.getOrgId());
      dto.setMajorName(majorInfo.getName());
    }
  }

  private long getLongByClockDaySetting(ClockDaySetting clockDaySetting) {
    return clockDaySetting.getYearMonth() * 100 + clockDaySetting.getDay();
  }

  @Override
  public PagedResult<CanStartCareRspDTO> canStartCarePage(String nameOrCode,
      Long orgId,
      Long majorId,
      Long instructorId,
      Integer pageNo,
      Integer pageSize,
      String orderBy,
      String descOrAsc, Long userId) {
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    Date yesterday = DateUtil.add(new Date(), Calendar.DAY_OF_YEAR, -1);
    //统计从昨天开始往前一个月打卡时间
    List<ClockDaySetting> clockDaySettingList =
        clockDaySettingService.list(DateUtil.add(yesterday, Calendar.DAY_OF_YEAR, -30), yesterday);

    clockDaySettingList.sort(new ClockDaySettingCompatator01());
    Collections.reverse(clockDaySettingList);
    if (!CollectionUtils.isEmpty(clockDaySettingList)) {
      long bigestDate = getLongByClockDaySetting(clockDaySettingList.get(0));
      if (bigestDate < DateUtil.getYearMonthDayByDate(yesterday)) {
        yesterday = DateUtil.strToDate(bigestDate + "", "yyyyMMdd");
      }
    }

    //算出从指定日开始的连续打卡日期
    List<ClockDaySetting> lxList = ConvertUtil.getLxClockDay(yesterday, clockDaySettingList);
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
    List<Long> lastClassIds = ConvertUtil
        .getLastClassIds(orgClassIds, majorClassIds, instructorClassIds);

    //根据classId和状态查询学生昨天的状态
    if (orgId != null || majorId != null || instructorId != null || userId != null) {
      if (CollectionUtils.isEmpty(lastClassIds)) {
        return PagedResult.success(pageNo, pageSize);
      }
    }
    List<Byte> clockStatus = new ArrayList<>();
    clockStatus.add(ClockStatus.STAYOUT.getType());
    clockStatus.add(ClockStatus.STAYOUT_LATE.getType());

    List<StudentClockStatusDO> studentClockStatusDOList =
        studentClockService.statStudentClockStatus(nameOrCode, lastClassIds, null,
            DateUtil.getYearMonthDayByDate(yesterday), clockStatus);
    if (CollectionUtils.isEmpty(studentClockStatusDOList)) {
      return PagedResult.success(pageNo, pageSize);
    }

    //去掉今天已经发起的
    List<Care> cares = careService.listByDate(new Date());
    List<Long> caredStudentIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(cares)) {
      for (Care c : cares) {
        caredStudentIds.add(c.getStudentId());
      }
    }

    //去掉昨晚不是晚归未归的
    Iterator<StudentClockStatusDO> iterator = studentClockStatusDOList.iterator();
    List<Long> needQueryList = new ArrayList<>();
    List<Long> studentIds = new ArrayList<>();
    while (iterator.hasNext()) {
      StudentClockStatusDO studentClockStatusDO = iterator.next();
      if (studentClockStatusDO.getClockStatus() != null
          && ClockStatus.STAYOUT.getType() == studentClockStatusDO.getClockStatus()) {
        studentClockStatusDO.setLxStayOut(1);
        needQueryList.add(studentClockStatusDO.getStudentId());
        studentIds.add(studentClockStatusDO.getStudentId());
      } else if (studentClockStatusDO.getClockStatus() != null
          && ClockStatus.STAYOUT_LATE.getType() != studentClockStatusDO.getClockStatus()) {
        studentClockStatusDO.setLxStayOutLate(1);
        needQueryList.add(studentClockStatusDO.getStudentId());
        studentIds.add(studentClockStatusDO.getStudentId());
      } else {
        iterator.remove();
        continue;
      }
      if (caredStudentIds.contains(studentClockStatusDO.getStudentId())) {
        iterator.remove();
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
                DateUtil.ymdTolong(clockDaySetting.getYearMonth(), clockDaySetting.getDay()), null);
        if (CommonBiz.calcLxNum(studentClockStatusDOList, needQueryList)) {
          break;
        }
      }
    }

    //满足1天以上未归或连续2天晚归学生
    if (!CollectionUtils.isEmpty(studentClockStatusDOList)) {
      Iterator<StudentClockStatusDO> iterator1 = studentClockStatusDOList.iterator();
      while (iterator1.hasNext()) {
        StudentClockStatusDO studentClockStatusDO = iterator1.next();
        if (studentClockStatusDO.getLxStayOutLate() >= 2
            || studentClockStatusDO.getLxStayOut() >= 1) {
        } else {
          iterator1.remove();
        }
      }
    }

    //计算累计被关怀
    List<StudentCareCountStatDO> studentCareCountStatDOS = careService
        .studentCareCountStat(studentIds);
    Map<Long, Integer> studentCareCountMap = ConvertUtil
        .getStudentCareCountMap(studentCareCountStatDOS);
    for (StudentClockStatusDO s : studentClockStatusDOList) {
      Integer count = studentCareCountMap.get(s.getStudentId());
      if (count != null) {
        s.setCared(count);
      } else {
        s.setCared(0);
      }
    }

    //连续未归：continuousStayoutDays 连续晚归：continuousStayoutLateDays
    if ("continuousStayoutDays".equals(orderBy)) {
      studentClockStatusDOList.sort(new StudentClockStatusCompatator01());
    } else if ("continuousStayoutLateDays".equals(orderBy)) {
      studentClockStatusDOList.sort(new StudentClockStatusCompatator02());
    } else if ("totalCared".equals(orderBy)) {
      studentClockStatusDOList.sort(new StudentClockStatusCompatator03());
    }

    //desc降序，asc升序
    if ("desc".equals(descOrAsc)) {
      Collections.reverse(studentClockStatusDOList);
    }

    PageInfo<StudentClockStatusDO> pageInfo = ListUtil
        .getPagingResultMap(studentClockStatusDOList, pageNo, pageSize);
    if (CollectionUtils.isEmpty(pageInfo.getList())) {
      return PagedResult.success(pageNo, pageSize);
    }

    List<CanStartCareRspDTO> canStartCareRspDTOList = new ArrayList<>();

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
    Map<Long, DormitoryUser> userDormitoryRefMap = ConvertUtil
        .getUserDormitoryRefMap(dormitoryUserList);

    Map<Long, BuildingInfo> buildingInfoMap = buildingCacheService.getBuildingInfoMap();

    List<Long> instructorIds = new ArrayList<>();
    for (StudentClockStatusDO s : pageInfo.getList()) {
      CanStartCareRspDTO dto = new CanStartCareRspDTO();
      dto.setContinuousStayoutDays(s.getLxStayOut());
      dto.setContinuousStayoutLateDays(s.getLxStayOutLate());
      dto.setTotalCared(s.getCared());
      dto.setStudentId(s.getStudentId());

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
        dto.setInstructorId(classInfo.getInstructorId());
        if (classInfo.getInstructorId() != null) {
          instructorIds.add(classInfo.getInstructorId());
        }
        MajorInfo majorInfo = majorInfoMap.get(classInfo.getMajorId());
        if (majorInfo != null) {
          CollegeInfo collegeInfo = collegeInfoMap.get(majorInfo.getOrgId());
          dto.setMajorName(majorInfo.getName());
          if (collegeInfo != null) {
            dto.setCollegeName(collegeInfo.getName());
          }
          dto.setCollegeId(majorInfo.getOrgId());
        }
      }
      DormitoryUser dormitoryUser = userDormitoryRefMap.get(s.getStudentId());
      setDormirotyAndBuilding(dormitoryInfoMap, buildingInfoMap, dto, dormitoryUser);
      canStartCareRspDTOList.add(dto);
    }

    if (!CollectionUtils.isEmpty(instructorIds) && !CollectionUtils
        .isEmpty(canStartCareRspDTOList)) {
      List<User> instructorList = userService.selectByPrimaryKeyList(instructorIds);
      Map<Long, User> instructorMap = ConvertUtil.getUserMap(instructorList);
      for (CanStartCareRspDTO dto : canStartCareRspDTOList) {
        User user = instructorMap.get(dto.getInstructorId());
        if (user != null) {
          dto.setInstructorName(user.getUserName());
        }
      }
    }
    //3.组装返回结果
    Page<CanStartCareRspDTO> canStartCareRspDTOPage = new Page<>();
    canStartCareRspDTOPage.setResult(canStartCareRspDTOList);
    canStartCareRspDTOPage.setTotalCount((int) pageInfo.getTotal());
    canStartCareRspDTOPage.setPageSize(pageSize);
    canStartCareRspDTOPage.setPageNo(pageNo);
    canStartCareRspDTOPage.setTotalPages(pageInfo.getPages());
    return PagedResult.success(canStartCareRspDTOPage);
  }

  private void setDormirotyAndBuilding(Map<Long, DormitoryInfo> dormitoryInfoMap,
      Map<Long, BuildingInfo> buildingInfoMap,
      CanStartCareRspDTO canStartCareRspDTO, DormitoryUser dormitoryUser) {
    if (dormitoryUser != null) {
      canStartCareRspDTO.setDormitoryId(dormitoryUser.getDormitoryId());
      canStartCareRspDTO.setBedCode(dormitoryUser.getBedCode());
      DormitoryInfo dormitoryInfo = dormitoryInfoMap.get(dormitoryUser.getDormitoryId());
      if (dormitoryInfo != null) {
        BuildingInfo buildingInfo = buildingInfoMap.get(dormitoryInfo.getBuildingId());
        if (buildingInfo != null) {
          canStartCareRspDTO.setBuildingName(buildingInfo.getName());
        }
        canStartCareRspDTO.setDormitoryName(dormitoryInfo.getName());
        canStartCareRspDTO.setBuildingId(dormitoryInfo.getBuildingId());
      }
    }
  }
}
