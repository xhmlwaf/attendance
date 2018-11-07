package com.yunhuakeji.attendance.service.baseservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.DormitoryUserMapper;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryUser;
import com.yunhuakeji.attendance.service.baseservice.DormitoryUserService;
import com.yunhuakeji.attendance.util.ListUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class DormitoryUserServiceImpl implements DormitoryUserService {

  @Autowired
  private DormitoryUserMapper dormitoryUserMapper;

  @Override
  public int insert(DormitoryUser record) {
    return dormitoryUserMapper.insert(record);
  }

  @Override
  public int deleteByPrimaryKey(Integer id) {
    return dormitoryUserMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(DormitoryUser record) {
    return dormitoryUserMapper.updateByPrimaryKeySelective(record);
  }

  @Override
  public DormitoryUser selectByPrimaryKey(Integer id) {
    return dormitoryUserMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<DormitoryUser> selectByPrimaryKeyList(List<Integer> ids) {
    Example example = new Example(DormitoryUser.class);
    example.createCriteria().andIn("id", ids);
    return dormitoryUserMapper.selectByExample(example);
  }

  @Override
  public Page<DormitoryUser> selectByRecordForPage(DormitoryUser record, int pageNo, int pageSize, String orderByClause) {
    Example example = new Example(DormitoryUser.class);
    Example.Criteria criteria = example.createCriteria();

    PageHelper.startPage(pageNo, pageSize);
    example.setOrderByClause(orderByClause);

    if (record.getDormitoryId() != null) {
      criteria.andEqualTo("dormitoryId", record.getDormitoryId());
    }
    if (record.getUserId() != null) {
      criteria.andEqualTo("userId", record.getUserId());
    }
    if (record.getState() != null) {
      criteria.andEqualTo("state", record.getState());
    }

    List<DormitoryUser> list = dormitoryUserMapper.selectByExample(example);
    PageInfo<DormitoryUser> pageInfo = new PageInfo<DormitoryUser>(list);
    Page<DormitoryUser> page = new Page<>();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    page.setResult(list);
    page.setTotalCount((int) pageInfo.getTotal());
    return page;
  }

  @Override
  public List<DormitoryUser> selectByRecordForList(DormitoryUser record) {
    Example example = new Example(DormitoryUser.class);
    Example.Criteria criteria = example.createCriteria();

    if (record.getDormitoryId() != null) {
      criteria.andEqualTo("dormitoryId", record.getDormitoryId());
    }
    if (record.getUserId() != null) {
      criteria.andEqualTo("userId", record.getUserId());
    }
    if (record.getState() != null) {
      criteria.andEqualTo("state", record.getState());
    }

    return dormitoryUserMapper.selectByExample(example);
  }

  @Override
  public Page<DormitoryUser> selectByParamsForPage(Long dormitoryId, Long userId, String state, int pageNo, int pageSize, String orderByClause) {
    Example example = new Example(DormitoryUser.class);
    Example.Criteria criteria = example.createCriteria();

    PageHelper.startPage(pageNo, pageSize);
    example.setOrderByClause(orderByClause);

    if (dormitoryId != null) {
      criteria.andEqualTo("dormitoryId", dormitoryId);
    }
    if (userId != null) {
      criteria.andEqualTo("userId", userId);
    }
    if (state != null) {
      criteria.andEqualTo("state", state);
    }

    List<DormitoryUser> list = dormitoryUserMapper.selectByExample(example);
    PageInfo<DormitoryUser> pageInfo = new PageInfo<DormitoryUser>(list);
    Page<DormitoryUser> page = new Page<>();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    page.setResult(list);
    page.setTotalCount((int) pageInfo.getTotal());
    return page;
  }

  @Override
  public List<DormitoryUser> selectByParamsForList(Long dormitoryId, Long userId, String state) {
    Example example = new Example(DormitoryUser.class);
    Example.Criteria criteria = example.createCriteria();

    if (dormitoryId != null) {
      criteria.andEqualTo("dormitoryId", dormitoryId);
    }
    if (userId != null) {
      criteria.andEqualTo("userId", userId);
    }
    if (state != null) {
      criteria.andEqualTo("state", state);
    }

    return dormitoryUserMapper.selectByExample(example);
  }

  @Override
  public List<DormitoryUser> listByDormitoryIds(List<Long> dormitoryIds) {
    Example example = new Example(DormitoryUser.class);
    Example.Criteria criteria = example.createCriteria();
    if (!CollectionUtils.isEmpty(dormitoryIds)) {
      criteria.andIn("dormitoryId", dormitoryIds);
    }
    return dormitoryUserMapper.selectByExample(example);
  }

  @Override
  public List<DormitoryUser> listByDormitoryId(long dormitoryId) {
    Example example = new Example(DormitoryUser.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("dormitoryId", dormitoryId);
    return dormitoryUserMapper.selectByExample(example);
  }

  @Override
  public List<DormitoryUser> listByUserIds(List<Long> userIds) {
    List<List<Long>> mulList = ListUtil.createList(userIds, 1000);
    List<DormitoryUser> dormitoryUserList = new ArrayList<>();
    for (List<Long> mids : mulList) {
      Example example = new Example(DormitoryUser.class);
      Example.Criteria criteria = example.createCriteria();
      if (!CollectionUtils.isEmpty(userIds)) {
        criteria.andIn("userId", mids);
      }
      List<DormitoryUser> dormitoryUsers = dormitoryUserMapper.selectByExample(example);
      if (!CollectionUtils.isEmpty(dormitoryUsers)) {
        for (DormitoryUser dormitoryUser : dormitoryUsers) {
          dormitoryUserList.add(dormitoryUser);
        }
      }
    }
    return dormitoryUserList;
  }

}
