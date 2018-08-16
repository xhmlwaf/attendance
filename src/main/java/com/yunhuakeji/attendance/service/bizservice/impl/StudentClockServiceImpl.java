package com.yunhuakeji.attendance.service.bizservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.bizdao.StudentClockHistoryMapper;
import com.yunhuakeji.attendance.dao.bizdao.StudentClockMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.BuildingClockStatDO;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusDO;
import com.yunhuakeji.attendance.dao.bizdao.model.ClockStatByStatusGenderDO;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockHistory;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockStatusCountStatDO;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import tk.mybatis.mapper.entity.Example;

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
    return studentClockMapper.selectCountByExample(example);
  }

  @Override
  public List<StudentClock> list(List<Long> studentIds, Long clockDate) {
    Example example = new Example(StudentClock.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIn("userId", studentIds);
    criteria.andEqualTo("clockStatus", clockDate);
    return studentClockMapper.selectByExample(example);
  }

  @Override
  public List<ClockStatByStatusDO> statByStatus(Map<String, Object> queryMap) {
    return studentClockMapper.statByStatus(queryMap);
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
  @Transactional
  public void updateClock(StudentClock studentClock, StudentClockHistory history) {
    //更新
    studentClockMapper.updateByPrimaryKeySelective(studentClock);

    studentClockHistoryMapper.insertSelective(history);
  }

  @Override
  public List<Long> listStudentIdsByIdsAndStatusAndDate(List<Long> studentIds, long clockDate, byte clockStatus) {
    Map<String, Object> queryMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(studentIds)) {
      queryMap.put("studentIds", studentIds);
    }
    queryMap.put("clockDate", clockDate);
    queryMap.put("clockStatus", clockStatus);
    return studentClockMapper.listStudentIdsByIdsAndStatusAndDate(queryMap);
  }

  @Override
  public List<StudentClockStatusCountStatDO> listStudentClockStatusCountStat(List<Long> studentIds, Date startDate, Date endDate, byte clockStatus) {
    Map<String, Object> queryMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(studentIds)) {
      queryMap.put("studentIds", studentIds);
    }
    queryMap.put("startClockDate", startDate);
    queryMap.put("endClockDate", endDate);
    queryMap.put("clockStatus", clockStatus);
    return studentClockMapper.listStudentClockStatusCountStat(queryMap);
  }


}

