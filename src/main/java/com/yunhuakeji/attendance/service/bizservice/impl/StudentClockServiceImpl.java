package com.yunhuakeji.attendance.service.bizservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.cache.StudentClockCache;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.bizdao.StudentClockHistoryMapper;
import com.yunhuakeji.attendance.dao.bizdao.StudentClockMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.*;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.ListUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentClockServiceImpl implements StudentClockService {

  @Autowired
  private StudentClockMapper studentClockMapper;

  @Autowired
  private StudentClockHistoryMapper studentClockHistoryMapper;

  @Override
  public List<StudentClock> list(Long studentId, Long clockDate) {
    Example example = new Example(StudentClock.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("userId", studentId);
    criteria.andEqualTo("clockDate", clockDate);
    return studentClockMapper.selectByExample(example);
  }

  @Override
  public List<StudentClock> listByMonth(Long studentId, Integer year, Integer month) {
    Example example = new Example(StudentClock.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("userId", studentId);
    long startDay = DateUtil.getYearMonthStartDay(year, month);
    long endDay = DateUtil.getYearMonthEndDay(year, month);
    criteria.andBetween("clockDate", startDay, endDay);
    return studentClockMapper.selectByExample(example);
  }

  @Override
  public void batchInsert(List<StudentClock> studentClockList) {
    studentClockMapper.insertBatchSelective(studentClockList);
  }

  @Override
  public int count(Long studentId, Byte clockStatus) {
    Example example = new Example(StudentClock.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("userId", studentId);
    criteria.andEqualTo("clockStatus", clockStatus);
    criteria.andLessThan("clockDate", DateUtil.currYYYYMMddToLong());
    return studentClockMapper.selectCountByExample(example);
  }

  @Override
  public List<StudentClock> list(List<Long> studentIds, Long clockDate) {
    List<List<Long>> mulList = ListUtil.createList(studentIds, 1000);
    List<StudentClock> studentClockList = new ArrayList<>();
    for (List<Long> mids : mulList) {
      Example example = new Example(StudentClock.class);
      Example.Criteria criteria = example.createCriteria();
      criteria.andIn("userId", mids);
      criteria.andEqualTo("clockDate", clockDate);
      List<StudentClock> ssss = studentClockMapper.selectByExample(example);
      if (!CollectionUtils.isEmpty(ssss)) {
        for (StudentClock studentClock : ssss) {
          studentClockList.add(studentClock);
        }
      }
    }
    return studentClockList;
  }

  @Override
  public List<StudentClock> listByTimeRange(Long studentId, long startClockTime, long endClockTime) {
    Example example = new Example(StudentClock.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("userId", studentId);
    criteria.andGreaterThanOrEqualTo("clockDate", startClockTime);
    criteria.andLessThanOrEqualTo("clockDate", endClockTime);
    example.setOrderByClause("CLOCK_DATE");
    return studentClockMapper.selectByExample(example);
  }

  @Override
  public List<ClockStatByStatusDO> statByStatus(Map<String, Object> queryMap) {
    return studentClockMapper.statByStatus(queryMap);
  }

  @Override
  public List<UserClockCountStatDO> statByUserStatus(Map<String, Object> queryMap) {
    return studentClockMapper.statByUserStatus(queryMap);
  }

  @Override
  public PageInfo<Long> getStudentIds(Map<String, Object> queryMap, int pageNo, int pageSize) {
    PageHelper.startPage(pageNo, pageSize);
    List<Long> studentIds = studentClockMapper.getStudentIds(queryMap);
    PageInfo pageInfo = new PageInfo(studentIds);
    return pageInfo;
  }

  @Override
  public List<ClockStatByStatusGenderDO> statByStatusGender(Map<String, Object> queryMap) {
    return studentClockMapper.statByStatusGender(queryMap);
  }

  @Override
  public List<BuildingClockStatDO> statByBuilding(long statDate) {
    return studentClockMapper.statByBuilding(statDate);
  }

  @Override
  public StudentClock getById(long id) {
    return studentClockMapper.selectByPrimaryKey(id);
  }

  @Override
  public StudentClock getByStudentIdAndDate(long studentId, long clockDate) {
    Example example = new Example(StudentClock.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("userId", studentId);
    criteria.andEqualTo("clockDate", clockDate);
    List<StudentClock> studentClockList = studentClockMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(studentClockList)) {
      return null;
    }
    return studentClockList.get(0);
  }

  @Override
  @Transactional
  public void updateClock(StudentClock studentClock, StudentClockHistory history) {
    //更新
    if (studentClock.getId() == null) {
      StudentClockCache.put(studentClock);
    } else {
      studentClockMapper.updateByPrimaryKeySelective(studentClock);
    }
    studentClockHistoryMapper.insertSelective(history);
  }

  @Override
  public List<Long> listStudentIdsByIdsAndStatusAndDate(List<Long> studentIds, long clockDate, byte clockStatus) {
    List<List<Long>> mulList = ListUtil.createList(studentIds, 1000);
    List<Long> studentIdList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(mulList)) {
      for (List<Long> subIds : mulList) {
        Map<String, Object> queryMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(subIds)) {
          queryMap.put("studentIds", subIds);
        }
        queryMap.put("clockDate", clockDate);
        queryMap.put("clockStatus", clockStatus);
        studentIdList.addAll(studentClockMapper.listStudentIdsByIdsAndStatusAndDate(queryMap));
      }
    }
    return studentIdList;
  }

  @Override
  public List<StudentClockStatusCountStatDO> listStudentClockStatusCountStat(List<Long> studentIds, Date startDate, Date endDate, byte clockStatus) {
    List<List<Long>> mulList = ListUtil.createList(studentIds, 1000);
    List<StudentClockStatusCountStatDO> studentClockStatusCountStatDOS = new ArrayList<>();
    if (!CollectionUtils.isEmpty(mulList)) {
      for (List<Long> subIds : mulList) {
        Map<String, Object> queryMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(studentIds)) {
          queryMap.put("studentIds", subIds);
        }
        queryMap.put("startClockDate", startDate);
        queryMap.put("endClockDate", endDate);
        queryMap.put("clockStatus", clockStatus);
        studentClockStatusCountStatDOS.addAll(studentClockMapper.listStudentClockStatusCountStat(queryMap));
      }
    }
    return studentClockStatusCountStatDOS;
  }

  @Override
  public List<StudentClockStatusDO> statStudentClockStatus(String nameOrCode, List<Long> classIds, List<Long> userIds, Long clockDate, List<Byte> clockStatus) {
    Map<String, Object> queryMap = new HashMap<>();
    if (nameOrCode != null) {
      queryMap.put("nameOrCode", nameOrCode);
    }
    if (!CollectionUtils.isEmpty(classIds)) {
      queryMap.put("classIds", classIds);
    }
    if (!CollectionUtils.isEmpty(userIds)) {
      queryMap.put("userIds", userIds);
    }
    queryMap.put("clockDate", clockDate);
    if (!CollectionUtils.isEmpty(clockStatus)) {
      queryMap.put("clockStatus", clockStatus);
    }
    return studentClockMapper.statStudentClockStatus(queryMap);
  }

  @Override
  public List<DateStatusCountStatDO> dateStatusCountStat(Long orgId, Date startClockDate, Date endClockDate) {
    Map<String, Object> queryMap = new HashMap<>();
    if (orgId != null) {
      queryMap.put("orgId", orgId);
    }
    queryMap.put("startClockDate", startClockDate);
    queryMap.put("endClockDate", endClockDate);
    return studentClockMapper.dateStatusCountStat(queryMap);
  }

  @Override
  public List<StudentStatusCountDO> studentStatusCountStat(String nameOrCode, List<Long> classIds, Date startClockDate, Date endClockDate) {
    Map<String, Object> queryMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(classIds)) {
      queryMap.put("classIds", classIds);
    }
    if (nameOrCode != null) {
      queryMap.put("nameOrCode", nameOrCode);
    }
    queryMap.put("startClockDate", startClockDate);
    queryMap.put("endClockDate", endClockDate);
    return studentClockMapper.studentStatusCountStat(queryMap);
  }

  @Override
  public List<StudentStatusCountDO> studentStatusCountStatByStudentIds(List<Long> studentIds, Date startClockDate, Date endClockDate) {
    Map<String, Object> queryMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(studentIds)) {
      queryMap.put("studentIds", studentIds);
    }
    queryMap.put("startClockDate", startClockDate);
    queryMap.put("endClockDate", endClockDate);
    return studentClockMapper.studentStatusCountStatByStudentIds(queryMap);
  }

  @Override
  public List<Long> getNotClockStudentIds(long clockDate) {
    return studentClockMapper.listNotClockStudentId(clockDate);
  }


}

