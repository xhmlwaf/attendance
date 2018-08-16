package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.AnalysisBiz;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.comparator.ClockDaySettingCompatator01;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockDaySetting;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusDO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByDayRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionClockByWeekRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByDayOfWeekRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByDayRsqDTO;
import com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByWeekRsqDTO;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.service.bizservice.ClockDaySettingService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class AnalysisBizImpl implements AnalysisBiz {

  @Autowired
  private StudentClockService studentClockService;

  @Autowired
  private ClockDaySettingService clockDaySettingService;

  @Autowired
  private MajorCacheService majorCacheService;

  @Autowired
  private ClassCacheService classCacheService;

  /**
   * 按学院时间统计晚归和未归
   *
   * @param orgId :
   * @param date :
   * @return : com.yunhuakeji.attendance.constants.Result<com.yunhuakeji.attendance.dto.response.AnalysisExceptionStatByDayRsqDTO>
   */
  @Override
  public Result<AnalysisExceptionStatByDayRsqDTO> getAnalysisExceptionStatByDay(Long orgId, Date date) {
    Map<String,Object> queryMap = new HashMap<>();
    queryMap.put("clockDate", DateUtil.getYearMonthDayByDate(date));
    if(orgId!=null){
      queryMap.put("orgId",orgId);
    }
    List<ClockStatByStatusDO> clockStatByStatusDOList = studentClockService.statByStatus(queryMap);
    AnalysisExceptionStatByDayRsqDTO dto = new AnalysisExceptionStatByDayRsqDTO();
    if(!CollectionUtils.isEmpty(clockStatByStatusDOList)){
      for(ClockStatByStatusDO clockStatByStatusDO:clockStatByStatusDOList){
        if(ClockStatus.STAYOUT.getType()==clockStatByStatusDO.getClockStatus()){
          dto.setLastNightStayoutNum(clockStatByStatusDO.getStatCount());
          dto.setLastNightStayoutLateNum(clockStatByStatusDO.getStatCount());
        }
      }
    }
    return Result.success(dto);
  }

  private List<Long> getClassIdsByOrgId(Long orgId){
    if(orgId==null){
      return null;
    }
    List<MajorInfo> majorInfoList = majorCacheService.list();
    List<Long> majorIds = new ArrayList<>();
    if(!CollectionUtils.isEmpty(majorInfoList)){
      for(MajorInfo majorInfo:majorInfoList){
         if(majorInfo.getOrgId().equals(orgId)){
           majorIds.add(majorInfo.getMajorId());
         }
      }
    }
    List<Long> classIds = new ArrayList<>();
    if(!CollectionUtils.isEmpty(majorIds)){
      List<ClassInfo> classInfoList = classCacheService.list();
      if(!CollectionUtils.isEmpty(classInfoList)){
        for(ClassInfo classInfo:classInfoList){
          if(majorIds.contains(classInfo.getMajorId())){
            classIds.add(classInfo.getClassId());
          }
        }
      }
    }
    return classIds;
  }

  private List<Long> getClassIdsByMajorId(Long majorId){
    if(majorId==null){
      return null;
    }
    List<Long> classIds = new ArrayList<>();
    List<ClassInfo> classInfoList = classCacheService.list();
    if(!CollectionUtils.isEmpty(classInfoList)){
      for(ClassInfo classInfo:classInfoList){
        if(classInfo.getMajorId().equals(majorId)){
          classIds.add(classInfo.getClassId());
        }
      }
    }
    return classIds;
  }

  private List<Long> getClassIdsByInstructorId(Long instructorId){
    if(instructorId==null){
      return null;
    }
    List<Long> classIds = new ArrayList<>();
    List<ClassInfo> classInfoList = classCacheService.list();
    if(!CollectionUtils.isEmpty(classInfoList)){
      for(ClassInfo classInfo:classInfoList){
        if(classInfo.getInstructorId().equals(instructorId)){
          classIds.add(classInfo.getClassId());
        }
      }
    }
    return classIds;
  }

  @Override
  public PagedResult<AnalysisExceptionClockByDayRsqDTO> getAnalysisExceptionClockByDay(String nameOrCode,
                                                                                       Long orgId,
                                                                                       Long majorId,
                                                                                       Long instructor,
                                                                                       Byte clockStatus,
                                                                                       Date date,
                                                                                       String orderBy,
                                                                                       String descOrAsc) {
    //统计从指定日往前一个月的打卡日期
    List<ClockDaySetting> clockDaySettingList =
            clockDaySettingService.list(DateUtil.add(date, Calendar.DAY_OF_YEAR,30),date);

    //算出从指定日开始的连续打卡日期
    List<ClockDaySetting> lxList = new ArrayList<>();
    if(!CollectionUtils.isEmpty(clockDaySettingList)){
      clockDaySettingList.sort(new ClockDaySettingCompatator01());
      Collections.reverse(clockDaySettingList);
      for(int i=0;i<clockDaySettingList.size();i++){
        if(dateEqual(clockDaySettingList.get(i), DateUtil.add(date,Calendar.DAY_OF_YEAR,-1*i))){
          lxList.add(clockDaySettingList.get(i));
        }else {
          break;
        }
      }
    }
    List<Long> orgClassIds = getClassIdsByOrgId(orgId);
    List<Long> majorClassIds = getClassIdsByMajorId(majorId);
    List<Long> instructorClassIds = getClassIdsByInstructorId(instructor);
    List<Long> lastClassIds = null;
    if(!CollectionUtils.isEmpty(orgClassIds)){
      lastClassIds = orgClassIds;
      if(!CollectionUtils.isEmpty(majorClassIds)){
        lastClassIds.retainAll(majorClassIds);
      }
      if(!CollectionUtils.isEmpty(instructorClassIds)){
        lastClassIds.retainAll(instructorClassIds);
      }
    } else if(!CollectionUtils.isEmpty(majorClassIds)){
      lastClassIds = majorClassIds;
      if(!CollectionUtils.isEmpty(instructorClassIds)){
        lastClassIds.retainAll(instructorClassIds);
      }
    } else if(!CollectionUtils.isEmpty(instructorClassIds)){
      lastClassIds = instructorClassIds;
    }





    return null;
  }

  private boolean dateEqual(ClockDaySetting clockDaySetting,Date date){
    if(DateUtil.getYearMonthByDate(date)==clockDaySetting.getYearMonth()
            &&DateUtil.getDayByDate(date)==clockDaySetting.getDay()){
      return true;
    }else {
      return false;
    }
  }

  @Override
  public Result<AnalysisExceptionStatByWeekRsqDTO> getAnalysisExceptionStatByWeek(Long orgId, int weekNumber) {
    return null;
  }

  @Override
  public Result<AnalysisExceptionStatByDayOfWeekRsqDTO> getAnalysisExceptionStatListByWeek(Long orgId, int weekNum) {
    return null;
  }

  @Override
  public PagedResult<AnalysisExceptionClockByWeekRsqDTO> getAnalysisExceptionClockByWeek(String nameOrCode, Long orgId, Long majorId, Long instructor, Byte clockStatus, int weekNum, String orderBy, String descOrAsc) {
    return null;
  }
}
