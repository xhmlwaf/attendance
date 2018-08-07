package com.yunhuakeji.attendance.service.baseservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.DormitoryInfoMapper;
import com.yunhuakeji.attendance.dao.basedao.DormitoryUserMapper;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.enums.State;
import com.yunhuakeji.attendance.service.baseservice.DormitoryInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.util.CollectionUtils;

import tk.mybatis.mapper.entity.Example;

@Service
public class DormitoryInfoServiceImpl implements DormitoryInfoService {

  @Autowired
  private DormitoryInfoMapper dormitoryInfoMapper;

  @Override
  public int insert(DormitoryInfo record) {
    return dormitoryInfoMapper.insert(record);
  }

  @Override
  public int deleteByPrimaryKey(String id) {
    return dormitoryInfoMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(DormitoryInfo record) {
    return dormitoryInfoMapper.updateByPrimaryKeySelective(record);
  }

  @Override
  public DormitoryInfo selectByPrimaryKey(String id) {
    return dormitoryInfoMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<DormitoryInfo> selectByPrimaryKeyList(List<String> ids) {
    Example example = new Example(DormitoryInfo.class);
    example.createCriteria().andIn("id", ids);
    return dormitoryInfoMapper.selectByExample(example);
  }

  @Override
  public List<DormitoryInfo> listAll() {
    Example example = new Example(DormitoryInfo.class);
    example.createCriteria().andEqualTo("state", State.NORMAL);
    return dormitoryInfoMapper.selectByExample(example);
  }

  @Override
  public Page<DormitoryInfo> selectByRecordForPage(DormitoryInfo record, int pageNo, int pageSize, String orderByClause) {
    Example example = new Example(DormitoryInfo.class);
    Example.Criteria criteria = example.createCriteria();

    PageHelper.startPage(pageNo, pageSize);
    example.setOrderByClause(orderByClause);

    if (record.getBuildingId() != null) {
      criteria.andEqualTo("buildingId", record.getBuildingId());
    }
    if (record.getFloorNumber() != null) {
      criteria.andEqualTo("floorNumber", record.getFloorNumber());
    }
    if (record.getState() != null) {
      criteria.andEqualTo("state", record.getState());
    }

    List<DormitoryInfo> list = dormitoryInfoMapper.selectByExample(example);
    PageInfo<DormitoryInfo> pageInfo = new PageInfo<DormitoryInfo>(list);
    Page<DormitoryInfo> page = new Page<>();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    page.setResult(list);
    page.setTotalCount((int) pageInfo.getTotal());
    return page;
  }

  @Override
  public List<DormitoryInfo> selectByRecordForList(DormitoryInfo record) {
    Example example = new Example(DormitoryInfo.class);
    Example.Criteria criteria = example.createCriteria();

    if (record.getBuildingId() != null) {
      criteria.andEqualTo("buildingId", record.getBuildingId());
    }
    if (record.getFloorNumber() != null) {
      criteria.andEqualTo("floorNumber", record.getFloorNumber());
    }
    if (record.getState() != null) {
      criteria.andEqualTo("state", record.getState());
    }

    return dormitoryInfoMapper.selectByExample(example);
  }

  @Override
  public Page<DormitoryInfo> selectByParamsForPage(Long buildingId, Short floorNumber, String state, int pageNo, int pageSize, String orderByClause) {
    Example example = new Example(DormitoryInfo.class);
    Example.Criteria criteria = example.createCriteria();

    PageHelper.startPage(pageNo, pageSize);
    example.setOrderByClause(orderByClause);

    if (buildingId != null) {
      criteria.andEqualTo("buildingId", buildingId);
    }
    if (floorNumber != null) {
      criteria.andEqualTo("floorNumber", floorNumber);
    }
    if (state != null) {
      criteria.andEqualTo("state", state);
    }

    List<DormitoryInfo> list = dormitoryInfoMapper.selectByExample(example);
    PageInfo<DormitoryInfo> pageInfo = new PageInfo<DormitoryInfo>(list);
    Page<DormitoryInfo> page = new Page<>();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    page.setResult(list);
    page.setTotalCount((int) pageInfo.getTotal());
    return page;
  }

  @Override
  public List<DormitoryInfo> selectByParamsForList(Long buildingId, Short floorNumber, String state) {
    Example example = new Example(DormitoryInfo.class);
    Example.Criteria criteria = example.createCriteria();

    if (buildingId != null) {
      criteria.andEqualTo("buildingId", buildingId);
    }
    if (floorNumber != null) {
      criteria.andEqualTo("floorNumber", floorNumber);
    }
    if (state != null) {
      criteria.andEqualTo("state", state);
    }

    return dormitoryInfoMapper.selectByExample(example);
  }

  @Override
  public List<DormitoryInfo> list(Long buildingId, Integer floorNumber) {
    Example example = new Example(DormitoryInfo.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("buildingId", buildingId);
    if (floorNumber != null) {
      criteria.andEqualTo("floorNumber", floorNumber);
    }
    criteria.andEqualTo("state", State.NORMAL.getState());
    return dormitoryInfoMapper.selectByExample(example);
  }

  @Override
  public List<DormitoryInfo> listDormitoryByInstructorId(Long instructorId) {
    List<DormitoryInfo> dormitoryInfoList = null;
    List<Long> dormitoryIdList = dormitoryInfoMapper.listDormitoryByInstructorId(instructorId);
    if (!CollectionUtils.isEmpty(dormitoryIdList)) {
      Example example = new Example(DormitoryInfo.class);
      Example.Criteria criteria = example.createCriteria();
      criteria.andIn("dormitoryId", dormitoryIdList);
      dormitoryInfoList = dormitoryInfoMapper.selectByExample(example);
    }
    return dormitoryInfoList;
  }

  @Override
  public List<DormitoryInfo> listByBuildingList(List<Long> buildingIds) {
    Example example = new Example(DormitoryInfo.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andIn("buildingId", buildingIds);
    criteria.andEqualTo("state", State.NORMAL.getState());
    return dormitoryInfoMapper.selectByExample(example);
  }

}

