package com.yunhuakeji.attendance.service.baseservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.BuildingInfoMapper;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.enums.BuildingType;
import com.yunhuakeji.attendance.enums.State;
import com.yunhuakeji.attendance.service.baseservice.BuildingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BuildingInfoServiceImpl implements BuildingInfoService {

    @Autowired
    private BuildingInfoMapper buildingInfoMapper;

    @Override
    public int insert(BuildingInfo record) {
        return buildingInfoMapper.insert(record);
    }


    @Override
    public int deleteByPrimaryKey(String id) {
        return buildingInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BuildingInfo record) {
        return buildingInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public BuildingInfo selectByPrimaryKey(String id) {
        return buildingInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BuildingInfo> selectByPrimaryKeyList(List<String> ids) {
        Example example = new Example(BuildingInfo.class);
        example.createCriteria().andIn("id", ids);
        return buildingInfoMapper.selectByExample(example);
    }

    @Override
    public List<BuildingInfo> listAll() {
        Example example = new Example(BuildingInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("state", State.NORMAL.getState());
        criteria.andEqualTo("type", BuildingType.SSL.getType());
        return buildingInfoMapper.selectByExample(example);
    }

    @Override
    public Page<BuildingInfo> selectByRecordForPage(BuildingInfo record, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(BuildingInfo.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo, pageSize);
        example.setOrderByClause(orderByClause);

        if (record.getType() != null) {
            criteria.andEqualTo("type", record.getType());
        }
        if (record.getUniversityId() != null) {
            criteria.andEqualTo("universityId", record.getUniversityId());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state", record.getState());
        }

        List<BuildingInfo> list = buildingInfoMapper.selectByExample(example);
        PageInfo<BuildingInfo> pageInfo = new PageInfo<BuildingInfo>(list);
        Page<BuildingInfo> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int) pageInfo.getTotal());
        return page;
    }

    @Override
    public List<BuildingInfo> selectByRecordForList(BuildingInfo record) {
        Example example = new Example(BuildingInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if (record.getType() != null) {
            criteria.andEqualTo("type", record.getType());
        }
        if (record.getUniversityId() != null) {
            criteria.andEqualTo("universityId", record.getUniversityId());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state", record.getState());
        }

        return buildingInfoMapper.selectByExample(example);
    }

    @Override
    public Page<BuildingInfo> selectByParamsForPage(Byte type, Long universityId, String state, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(BuildingInfo.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo, pageSize);
        example.setOrderByClause(orderByClause);

        if (type != null) {
            criteria.andEqualTo("type", type);
        }
        if (universityId != null) {
            criteria.andEqualTo("universityId", universityId);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }

        List<BuildingInfo> list = buildingInfoMapper.selectByExample(example);
        PageInfo<BuildingInfo> pageInfo = new PageInfo<BuildingInfo>(list);
        Page<BuildingInfo> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int) pageInfo.getTotal());
        return page;
    }

    @Override
    public List<BuildingInfo> selectByParamsForList(Byte type, Long universityId, String state) {
        Example example = new Example(BuildingInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if (type != null) {
            criteria.andEqualTo("type", type);
        }
        if (universityId != null) {
            criteria.andEqualTo("universityId", universityId);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }

        return buildingInfoMapper.selectByExample(example);
    }

}
