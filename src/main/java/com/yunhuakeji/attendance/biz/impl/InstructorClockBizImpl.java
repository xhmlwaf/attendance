package com.yunhuakeji.attendance.biz.impl;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.aspect.RequestLog;
import com.yunhuakeji.attendance.biz.CommonHandlerUtil;
import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.InstructorClockBiz;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.cache.QrCodeCache;
import com.yunhuakeji.attendance.comparator.InstructorCompatator01;
import com.yunhuakeji.attendance.comparator.InstructorCompatator02;
import com.yunhuakeji.attendance.comparator.InstructorCompatator03;
import com.yunhuakeji.attendance.comparator.InstructorCompatator04;
import com.yunhuakeji.attendance.comparator.InstructorCompatator05;
import com.yunhuakeji.attendance.constants.ConfigConstants;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorCareCountStat;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClock;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClockCountStat;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;
import com.yunhuakeji.attendance.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockDetailRspDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockStatRsqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorStatRspDTO;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.CareService;
import com.yunhuakeji.attendance.service.bizservice.InstructorClockService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.UserOrgRefService;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.ListUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InstructorClockBizImpl implements InstructorClockBiz {

  private static final Logger logger = LoggerFactory.getLogger(InstructorClockBizImpl.class);

  @Autowired
  private InstructorClockService instructorClockService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private UserClassService userClassService;

  @Autowired
  private CareService careService;

  @Autowired
  private UserService userService;

  @Autowired
  private OrgCacheService orgCacheService;

  @Autowired
  private StudentClockService studentClockService;

  @Autowired
  private QrCodeCache qrCodeCache;

  @Autowired
  private UserOrgRefService userOrgRefService;

  @Override
  public Result<InstructorClockStatRsqDTO> statAllCount(Long instructorId) {
    int statCount = instructorClockService.statByInstructor(instructorId);
    InstructorClockStatRsqDTO rsqDTO = new InstructorClockStatRsqDTO();
    rsqDTO.setTotalClockCount(statCount);
    return Result.success(rsqDTO);
  }

  @Override
  public Result instructorClock(InstructorClockReqDTO req) {

    String qrCode = req.getQrCode();
    boolean checkResult = qrCodeCache.checkQrCode(qrCode);
    if (!checkResult) {
      throw new BusinessException(ErrorCode.QR_CODE_IS_EXPIRE);
    }
    long yearMonthDay = DateUtil.currYYYYMMddToLong();
    List<InstructorClock> instructorClockList =
        instructorClockService.list(req.getInstructorId(), yearMonthDay);
    if (!CollectionUtils.isEmpty(instructorClockList)) {
      throw new BusinessException(ErrorCode.INSTRUCTOR_HAS_CLOCK);
    }
    InstructorClock instructorClock = new InstructorClock();
    instructorClock.setId(DateUtil.uuid());
    instructorClock.setClockTime(new Date());
    instructorClock.setInstructorId(req.getInstructorId());
    instructorClock.setStatDate(yearMonthDay);
    instructorClockService.save(instructorClock);

    return Result.success();
  }

  @Override
  public Result<List<String>> statByYearAndMonth(Long instructorId, Integer year, Integer month) {

    List<InstructorClock> instructorClockList = instructorClockService.list(instructorId, year, month);
    List<String> resultList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(instructorClockList)) {
      for (InstructorClock instructorClock : instructorClockList) {
        resultList.add(DateUtil.dateToStr(instructorClock.getClockTime(), DateUtil.DATESTYLE_YYYYMMDD_HH_MM_SS));
      }
    }
    return Result.success(resultList);
  }

  @Override
  public Result<Byte> getInstructorClockStatusByDay(Long instructorId) {
    List<InstructorClock> instructorClockList = instructorClockService.list(instructorId, DateUtil.currYYYYMMddToLong());
    if (CollectionUtils.isEmpty(instructorClockList)) {
      return Result.success(ConfigConstants.INSTRUCTOR_NOT_CLOCK);
    }
    return Result.success(ConfigConstants.INSTRUCTOR_CLOCK);
  }

  @Override
  public PagedResult<InstructorStatRspDTO> instructorStatPage(String nameOrCode,
                                                              Long orgId, Integer pageNo,
                                                              Integer pageSize,
                                                              String orderBy,
                                                              String descOrAsc) {
    nameOrCode = CommonHandlerUtil.trimNameOrCode(nameOrCode);
    Map<Long, List<Long>> instructorClassMap = classCacheService.getInstructorClassMap();
    List<Long> instructorIds = classCacheService.getInstructorIds();
    List<Long> classIds = classCacheService.getClassIds();
    Map<Long, CollegeInfo> collegeInfoMap = orgCacheService.getCollegeInfoMap();
    //可以算出负责学生
    List<UserClass> userClassList = userClassService.listStudentByClassIds(classIds);
    Map<Long, List<Long>> classUserMap = ConvertUtil.getClassUserListMap(userClassList);
    //打卡次数
    List<InstructorClockCountStat> instructorClockCountStatList =
        instructorClockService.instructorClockCountStatByIds(instructorIds);
    Map<Long, Integer> instructorClockCountMap = getInstructorClockCountMap(instructorClockCountStatList);
    List<InstructorCareCountStat> instructorCareCountStatList = careService.instructorCareCountStat(instructorIds);
    Map<Long, Integer> instructorCareCountMap = getInstructorCareCountMap(instructorCareCountStatList);
    List<InstructorStatRspDTO> instructorStatRspDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(instructorIds)) {
      List<User> userList = userService.selectByPrimaryKeyList(instructorIds);

      List<UserOrgRef> userOrgRefList = userOrgRefService.listByUserIds(instructorIds);
      Map<Long, Long> userOrgMap = ConvertUtil.getUserOrgMap(userOrgRefList);
      Map<Long, User> userMap = ConvertUtil.getUserMap(userList);
      for (Long id : instructorIds) {
        InstructorStatRspDTO dto = new InstructorStatRspDTO();
        dto.setUserId(id);
        User user = userMap.get(id);
        if (user != null) {
          dto.setName(user.getUserName());
          dto.setCode(user.getCode());
          dto.setClockCount(instructorClockCountMap.get(id) != null ? instructorClockCountMap.get(id) : 0);
          dto.setDealCareCount(instructorCareCountMap.get(id) != null ? instructorCareCountMap.get(id) : 0);
          List<Long> fuzeClassIds = instructorClassMap.get(id);
          //负责学生数
          List<Long> userIds = null;
          if (!CollectionUtils.isEmpty(fuzeClassIds)) {
            userIds = new ArrayList<>();
            for (Long classId : fuzeClassIds) {
              List<Long> cUserIds = classUserMap.get(classId);
              if (!CollectionUtils.isEmpty(cUserIds)) {
                for (Long uid : cUserIds) {
                  userIds.add(uid);
                }
              }
            }
          }

          if (CollectionUtils.isEmpty(userIds)) {
            dto.setResponsibleStudent(0);
          } else {
            dto.setResponsibleStudent(userIds.size());
            //统计打卡情况
            List<StudentClock> studentClockList = studentClockService.list(userIds, DateUtil.currYYYYMMddToLong());
            int stayOut = 0;
            int stayOutLate = 0;
            if (!CollectionUtils.isEmpty(studentClockList)) {
              for (StudentClock studentClock : studentClockList) {
                if (studentClock.getClockStatus() != null && ClockStatus.STAYOUT.getType() == studentClock.getClockStatus()) {
                  stayOut++;
                } else if (studentClock.getClockStatus() != null && ClockStatus.STAYOUT_LATE.getType() == studentClock.getClockStatus()) {
                  stayOutLate++;
                }
              }
            }
            dto.setTotalLayOutCount(stayOut);
            dto.setTotalLayOutLateCount(stayOutLate);
          }
          Long currOrgId = userOrgMap.get(id);
          if (currOrgId != null) {
            CollegeInfo collegeInfo = collegeInfoMap.get(currOrgId);
            if (collegeInfo != null) {
              dto.setCollegeId(collegeInfo.getOrgId());
              dto.setCollegeName(collegeInfo.getName());
            }
          }
          if (!StringUtils.isEmpty(nameOrCode)) {
            if (dto.getName().indexOf(nameOrCode) >= 0 || dto.getCode().indexOf(nameOrCode) >= 0) {
            } else {
              continue;
            }
          }
          if (orgId != null && !orgId.equals(currOrgId)) {
            continue;
          }
          instructorStatRspDTOList.add(dto);
        }
      }
    }

    //排序 responsibleStudent 打卡次数：clockCount 处理关怀数:dealCareCount 累计未归学生:totalLayOutCount 累计晚归学生:totalLayOutLateCount
    if ("responsibleStudent".equals(orderBy)) {
      instructorStatRspDTOList.sort(new InstructorCompatator01());
    } else if ("clockCount".equals(orderBy)) {
      instructorStatRspDTOList.sort(new InstructorCompatator02());
    } else if ("dealCareCount".equals(orderBy)) {
      instructorStatRspDTOList.sort(new InstructorCompatator03());
    } else if ("totalLayOutCount".equals(orderBy)) {
      instructorStatRspDTOList.sort(new InstructorCompatator04());
    } else if ("totalLayOutLateCount".equals(orderBy)) {
      instructorStatRspDTOList.sort(new InstructorCompatator05());
    }

    //升序或降序 desc降序，asc升序
    if ("desc".equals(descOrAsc)) {
      Collections.reverse(instructorStatRspDTOList);
    }
    PageInfo pageInfo = ListUtil.getPagingResultMap(instructorStatRspDTOList, pageNo, pageSize);

    //3.组装返回结果
    Page<InstructorStatRspDTO> instructorStatRspDTOPage = new Page<>();
    instructorStatRspDTOPage.setResult(pageInfo.getList());
    instructorStatRspDTOPage.setTotalCount((int) pageInfo.getTotal());
    instructorStatRspDTOPage.setPageSize(pageSize);
    instructorStatRspDTOPage.setPageNo(pageNo);
    instructorStatRspDTOPage.setTotalPages(pageInfo.getPages());

    return PagedResult.success(instructorStatRspDTOPage);
  }

  public static void main(String[] args) {
    System.out.println("xxx".indexOf("xxx"));
  }

  @Override
  public PagedResult<InstructorClockDetailRspDTO> statAllClock(Long instructorId, Integer pageNo, Integer pageSize) {

    PageInfo<InstructorClock> pageInfo = instructorClockService.page(instructorId, pageNo, pageSize);
    if (CollectionUtils.isEmpty(pageInfo.getList())) {
      return PagedResult.success(pageNo, pageSize);
    }
    User user = userService.selectByPrimaryKey(instructorId);
    if (user == null) {
      throw new BusinessException(ErrorCode.INSTRUCTOR_NOT_EXSIT);
    }
    List<InstructorClockDetailRspDTO> instructorClockDetailRspDTOS = pageInfo.getList().stream().map(e -> {
      InstructorClockDetailRspDTO dto = new InstructorClockDetailRspDTO();
      dto.setClockTime(e.getClockTime());
      dto.setCode(user.getCode());
      dto.setName(user.getUserName());
      return dto;
    }).collect(Collectors.toList());

    //3.组装返回结果
    Page<InstructorClockDetailRspDTO> instructorClockDetailRspDTOPage = new Page<>();
    instructorClockDetailRspDTOPage.setResult(instructorClockDetailRspDTOS);
    instructorClockDetailRspDTOPage.setTotalCount((int) pageInfo.getTotal());
    instructorClockDetailRspDTOPage.setPageSize(pageSize);
    instructorClockDetailRspDTOPage.setPageNo(pageNo);
    instructorClockDetailRspDTOPage.setTotalPages(pageInfo.getPages());
    return PagedResult.success(instructorClockDetailRspDTOPage);
  }

  private Map<Long, Integer> getInstructorClockCountMap(List<InstructorClockCountStat> instructorClockCountStatList) {
    if (!CollectionUtils.isEmpty(instructorClockCountStatList)) {
      return instructorClockCountStatList.stream().collect(Collectors.toMap(InstructorClockCountStat::getInstructorId, InstructorClockCountStat::getStatCount, (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  private Map<Long, Integer> getInstructorCareCountMap(List<InstructorCareCountStat> instructorCareCountStatList) {
    if (!CollectionUtils.isEmpty(instructorCareCountStatList)) {
      return instructorCareCountStatList.stream().collect(Collectors.toMap(InstructorCareCountStat::getInstructorId, InstructorCareCountStat::getStatCount, (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }
}
