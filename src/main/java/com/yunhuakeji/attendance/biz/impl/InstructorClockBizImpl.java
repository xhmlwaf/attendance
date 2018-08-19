package com.yunhuakeji.attendance.biz.impl;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.biz.CommonHandlerUtil;
import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.biz.InstructorClockBiz;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.comparator.InstructorCompatator01;
import com.yunhuakeji.attendance.comparator.InstructorCompatator02;
import com.yunhuakeji.attendance.comparator.InstructorCompatator03;
import com.yunhuakeji.attendance.comparator.InstructorCompatator04;
import com.yunhuakeji.attendance.comparator.InstructorCompatator05;
import com.yunhuakeji.attendance.constants.ConfigConstants;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorCareCountStat;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClock;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClockCountStat;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockStatRsqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorStatRspDTO;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.CareService;
import com.yunhuakeji.attendance.service.bizservice.InstructorClockService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.ListUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InstructorClockBizImpl implements InstructorClockBiz {

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
  private MajorCacheService majorCacheService;

  @Autowired
  private OrgCacheService orgCacheService;

  @Autowired
  private StudentClockService studentClockService;

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
    //TODO 校验二维码是否正确

    long yearMonthDay = DateUtil.currYYYYMMddToLong();
    List<InstructorClock> instructorClockList =
        instructorClockService.list(req.getInstructorId(), yearMonthDay);
    if (!CollectionUtils.isEmpty(instructorClockList)) {
      //TODO 已经打卡了
    }
    InstructorClock instructorClock = new InstructorClock();
    instructorClock.setClockTime(new Date());
    instructorClock.setInstructorId(req.getInstructorId());
    instructorClock.setStatDate((long) yearMonthDay);
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
    List<InstructorClock> instructorClockList = instructorClockService.list(instructorId, DateUtil.currHhmmssToLong());
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
    nameOrCode = CommonHandlerUtil.likeNameOrCode(nameOrCode);
    Map<Long, Long> instructorClassMap = classCacheService.getInstructorClassMap();
    List<Long> instructorIds = classCacheService.getInstructorIds();
    List<Long> classIds = classCacheService.getClassIds();
    Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
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
          Long classId = instructorClassMap.get(id);
          List<Long> userIds = classUserMap.get(classId);
          if (CollectionUtils.isEmpty(userIds)) {
            dto.setResponsibleStudent(0);
          } else {
            dto.setResponsibleStudent(userIds.size());
            //统计打卡情况
            List<StudentClock> studentClockList = studentClockService.list(userIds, DateUtil.currHhmmssToLong());
            int stayOut = 0;
            int stayOutLate = 0;
            if (!CollectionUtils.isEmpty(studentClockList)) {
              for (StudentClock studentClock : studentClockList) {
                if (ClockStatus.STAYOUT.getType() == studentClock.getClockStatus()) {
                  stayOut++;
                } else if (ClockStatus.STAYOUT_LATE.getType() == studentClock.getClockStatus()) {
                  stayOutLate++;
                }
              }
            }
            dto.setTotalLayOutCount(stayOut);
            dto.setTotalLayOutLateCount(stayOutLate);
          }
          MajorInfo majorInfo = majorInfoMap.get(classId);
          if (majorInfo != null) {
            CollegeInfo collegeInfo = collegeInfoMap.get(majorInfo.getOrgId());
            if (collegeInfo != null) {
              dto.setCollegeId(collegeInfo.getOrgId());
              dto.setCollegeName(collegeInfo.getName());
            }
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

  @Override
  public void statExportExcel(Long instructorId) {


  }

  private Map<Long, Integer> getInstructorClockCountMap(List<InstructorClockCountStat> instructorClockCountStatList) {
    Map<Long, Integer> instructorClockCountMap = new HashMap<>();
    if (CollectionUtils.isEmpty(instructorClockCountStatList)) {
      for (InstructorClockCountStat stat : instructorClockCountStatList) {
        instructorClockCountMap.put(stat.getInstructorId(), stat.getStatCount());
      }
    }
    return instructorClockCountMap;
  }

  private Map<Long, Integer> getInstructorCareCountMap(List<InstructorCareCountStat> instructorCareCountStatList) {
    Map<Long, Integer> instructorCareCountMap = new HashMap<>();
    if (CollectionUtils.isEmpty(instructorCareCountStatList)) {
      for (InstructorCareCountStat stat : instructorCareCountStatList) {
        instructorCareCountMap.put(stat.getInstructorId(), stat.getStatCount());
      }
    }
    return instructorCareCountMap;
  }
}
