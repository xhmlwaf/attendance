package com.yunhuakeji.attendance.service.baseservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.UserClassMapper;
import com.yunhuakeji.attendance.dao.basedao.model.InstructorInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.enums.State;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.util.ListUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserClassServiceImpl implements UserClassService {

  @Autowired
  private UserClassMapper userClassMapper;

  @Override
  public int insert(UserClass record) {
    return userClassMapper.insert(record);
  }

  @Override
  public int deleteByPrimaryKey(String id) {
    return userClassMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(UserClass record) {
    return userClassMapper.updateByPrimaryKeySelective(record);
  }

  @Override
  public UserClass selectByPrimaryKey(String id) {
    return userClassMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<UserClass> selectByPrimaryKeyList(List<String> ids) {
    Example example = new Example(UserClass.class);
    example.createCriteria().andIn("id", ids);
    return userClassMapper.selectByExample(example);
  }

  @Override
  public Page<UserClass> selectByRecordForPage(UserClass record, int pageNo, int pageSize, String orderByClause) {
    Example example = new Example(UserClass.class);
    Example.Criteria criteria = example.createCriteria();

    PageHelper.startPage(pageNo, pageSize);
    example.setOrderByClause(orderByClause);

    if (record.getClassId() != null) {
      criteria.andEqualTo("classId", record.getClassId());
    }
    if (record.getState() != null) {
      criteria.andEqualTo("state", record.getState());
    }

    List<UserClass> list = userClassMapper.selectByExample(example);
    PageInfo<UserClass> pageInfo = new PageInfo<UserClass>(list);
    Page<UserClass> page = new Page<>();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    page.setResult(list);
    page.setTotalCount((int) pageInfo.getTotal());
    return page;
  }

  @Override
  public List<UserClass> selectByRecordForList(UserClass record) {
    Example example = new Example(UserClass.class);
    Example.Criteria criteria = example.createCriteria();

    if (record.getClassId() != null) {
      criteria.andEqualTo("classId", record.getClassId());
    }
    if (record.getState() != null) {
      criteria.andEqualTo("state", record.getState());
    }

    return userClassMapper.selectByExample(example);
  }

  @Override
  public Page<UserClass> selectByParamsForPage(Long classId, String state, int pageNo, int pageSize, String orderByClause) {
    Example example = new Example(UserClass.class);
    Example.Criteria criteria = example.createCriteria();

    PageHelper.startPage(pageNo, pageSize);
    example.setOrderByClause(orderByClause);

    if (classId != null) {
      criteria.andEqualTo("classId", classId);
    }
    if (state != null) {
      criteria.andEqualTo("state", state);
    }

    List<UserClass> list = userClassMapper.selectByExample(example);
    PageInfo<UserClass> pageInfo = new PageInfo<UserClass>(list);
    Page<UserClass> page = new Page<>();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    page.setResult(list);
    page.setTotalCount((int) pageInfo.getTotal());
    return page;
  }

  @Override
  public List<UserClass> selectByParamsForList(Long classId, String state) {
    Example example = new Example(UserClass.class);
    Example.Criteria criteria = example.createCriteria();

    if (classId != null) {
      criteria.andEqualTo("classId", classId);
    }
    if (state != null) {
      criteria.andEqualTo("state", state);
    }

    return userClassMapper.selectByExample(example);
  }

  @Override
  public List<UserClass> listByUserIds(List<Long> userIds) {
    List<List<Long>> mulList = ListUtil.createList(userIds, 1000);
    List<UserClass> userClassList = new ArrayList<>();
    for (List<Long> mids : mulList) {
      Example example = new Example(UserClass.class);
      Example.Criteria criteria = example.createCriteria();
      if (!CollectionUtils.isEmpty(userIds)) {
        criteria.andIn("userId", mids);
      }
      criteria.andEqualTo("state", State.NORMAL.getState());
      userClassList.addAll(userClassMapper.selectByExample(example));
    }
    return userClassList;
  }

  @Override
  public PageInfo<InstructorInfo> listInstructorInfo(String nameOrCode, int pageNo, int pageSize) {
    PageHelper.startPage(pageNo, pageSize);
    List<InstructorInfo> instructorInfoList = userClassMapper.queryInstructorByNameAndCode(nameOrCode);
    PageInfo pageInfo = new PageInfo(instructorInfoList);
    return pageInfo;
  }

  @Override
  public List<UserClass> listStudentByClassIds(List<Long> classIds) {
    return userClassMapper.listStudentByClassIds(classIds);
  }

}
