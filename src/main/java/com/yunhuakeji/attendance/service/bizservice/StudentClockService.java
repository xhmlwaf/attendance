package com.yunhuakeji.attendance.service.bizservice;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.bizdao.model.*;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StudentClockService {

  List<StudentClock> list(Long studentId, Long clockDate);

  List<StudentClock> listByMonth(Long studentId, Integer year, Integer month);

  void batchInsert(List<StudentClock> studentClockList);

  int count(Long studentId, Byte clockStatus);

  /**
   * 统计一批学生在某天的打卡状态
   *
   * @param studentIds :
   * @param clockDate  :
   * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.StudentClock>
   */
  List<StudentClock> list(List<Long> studentIds, Long clockDate);

  List<StudentClock> listByTimeRange(Long studentId, long startClockTime,long endClockTime);

  /**
   * 根据instructorId,buildingIds,clockDate统计
   *
   * @param queryMap :
   * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusDO>
   */
  List<ClockStatByStatusDO> statByStatus(Map<String, Object> queryMap);


  List<UserClockCountStatDO> statByUserStatus(Map<String, Object> queryMap);

  /**
   * 据instructorId,buildingIds,clockDate,CLOCK_STATUS查询学生ID
   *
   * @param queryMap :
   * @return : java.util.List<java.lang.Long>
   */
  PageInfo<Long> getStudentIds(Map<String, Object> queryMap, int pageNo, int pageSize);

  /**
   * 根据打卡状态和性别统计
   *
   * @param queryMap :
   * @return : java.util.List<com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusGenderDO>
   */
  List<ClockStatByStatusGenderDO> statByStatusGender(Map<String, Object> queryMap);


  List<BuildingClockStatDO> statByBuilding(long statDate);

  StudentClock getById(long id);

  StudentClock getByStudentIdAndDate(long studentId,long clockDate);

  void updateClock(StudentClockDTO studentClock, StudentClockHistory history);

  List<Long> listStudentIdsByIdsAndStatusAndDate(List<Long> studentIds, long clockDate, byte clockStatus);

  List<StudentClockStatusCountStatDO> listStudentClockStatusCountStat(List<Long> studentIds, Date startDate, Date endDate, byte clockStatus);


  List<StudentClockStatusDO> statStudentClockStatus(String nameOrCode,List<Long> classIds,List<Long> userIds,Long clockDate,List<Byte> clockStatus);

  List<DateStatusCountStatDO> dateStatusCountStat(List<Long> orgIds,Date startClockDate,Date endClockDate);

  List<StudentStatusCountDO> studentStatusCountStat(String nameOrCode,List<Long> classIds,Date startClockDate,Date endClockDate);

  List<StudentStatusCountDO> studentStatusCountStatByStudentIds(List<Long> studentids,Date startClockDate,Date endClockDate);

  List<Long> getNotClockStudentIds(long clockDate);


}
