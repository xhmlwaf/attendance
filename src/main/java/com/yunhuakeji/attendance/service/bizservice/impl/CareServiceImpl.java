package com.yunhuakeji.attendance.service.bizservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.bizdao.CareMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.Care;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorCareCountStat;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentCareCountStatDO;
import com.yunhuakeji.attendance.enums.CareStatus;
import com.yunhuakeji.attendance.service.bizservice.CareService;

import com.yunhuakeji.attendance.util.DateUtil;
import com.yunhuakeji.attendance.util.ListUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import tk.mybatis.mapper.entity.Example;

@Service
public class CareServiceImpl implements CareService {

  @Autowired
  private CareMapper careMapper;

  @Override
  public PageInfo pageByInstructor(long instructorId, Byte careStatus, int pageNo, int pageSize) {
    Example example = new Example(Care.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("instructorId", instructorId);
    criteria.andEqualTo("careStatus", careStatus);
    PageHelper.startPage(pageNo, pageSize);
    List<Care> careList = careMapper.selectByExample(example);
    PageInfo pageInfo = new PageInfo(careList);

    return pageInfo;
  }

  @Override
  public void update(Care care) {
    care.setDealTime(new Date());
    careMapper.updateByPrimaryKeySelective(care);
  }

  @Override
  public PageInfo pageByStudent(long studentId, int pageNo, int pageSize) {
    Example example = new Example(Care.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("studentId", studentId);
    criteria.andEqualTo("careStatus", CareStatus.YES.getType());
    PageHelper.startPage(pageNo, pageSize);
    List<Care> careList = careMapper.selectByExample(example);
    PageInfo pageInfo = new PageInfo(careList);

    return pageInfo;
  }

  @Override
  public void batchInsert(List<Care> careList) {
    for (Care care : careList) {
      careMapper.insertSelective(care);
    }
  }

  @Override
  public List<Care> listByIds(List<Long> ids) {
    Example example = new Example(Care.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIn("id", ids);
    return careMapper.selectByExample(example);
  }

  @Override
  public void batchDelete(List<Long> ids) {
    Example example = new Example(Care.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIn("id", ids);
    careMapper.deleteByExample(example);
  }

  @Override
  public List<InstructorCareCountStat> instructorCareCountStat(List<Long> instructorIds) {
    return careMapper.instructorCareCountStat(instructorIds);
  }

  @Override
  public PageInfo<Care> pageByClassIdsAndStatus(List<Long> classIds, String nameOrCode, Byte careStatus, Integer pageNo, Integer pageSize) {
    Map<String, Object> queryMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(classIds)) {
      queryMap.put("classIds", classIds);
    }

    if (StringUtils.isNotBlank(nameOrCode)) {
      queryMap.put("nameOrCode", nameOrCode);
    }
    if (careStatus != null) {
      queryMap.put("careStatus", careStatus);
    }
    PageHelper.startPage(pageNo, pageSize);
    List<Care> careList = careMapper.listByClassIdsAndStatus(queryMap);
    PageInfo<Care> pageInfo = new PageInfo<>(careList);
    return pageInfo;
  }

  @Override
  public List<StudentCareCountStatDO> studentCareCountStat(List<Long> studentIds) {
    List<List<Long>> mulList = ListUtil.createList(studentIds, 1000);
    List<StudentCareCountStatDO> studentCareCountStatDOS = new ArrayList<>();
    for (List<Long> mids : mulList) {
      Map<String, Object> queryMap = new HashMap<>();
      if (!CollectionUtils.isEmpty(mids)) {
        queryMap.put("studentIds", mids);
      }
      studentCareCountStatDOS.addAll(careMapper.studentCareCountStat(queryMap));
    }
    return studentCareCountStatDOS;
  }

  @Override
  public List<Care> listByIdsAndDate(List<Long> userIds) {
    Example example = new Example(Care.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIn("studentId", userIds);
    Date date = new Date();
    criteria.andGreaterThanOrEqualTo("originateTime", DateUtil.getDateStartTime(date));
    criteria.andLessThan("originateTime", DateUtil.getDateEndTime(date));
    careMapper.deleteByExample(example);
    return null;
  }


}
