package com.yunhuakeji.attendance.service.baseservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.CollegeInfoMapper;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.enums.State;
import com.yunhuakeji.attendance.service.baseservice.CollegeInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class CollegeInfoServiceImpl implements CollegeInfoService {

    @Autowired
    private CollegeInfoMapper collegeInfoMapper;

    @Override
    public int insert(CollegeInfo record) {
        return collegeInfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return collegeInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CollegeInfo record) {
        return collegeInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CollegeInfo selectByPrimaryKey(Long id) {
        return collegeInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CollegeInfo> selectByPrimaryKeyList(List<String> ids) {
        Example example = new Example(CollegeInfo.class);
        example.createCriteria().andIn("id", ids);
        return collegeInfoMapper.selectByExample(example);
    }

    @Override
    public List<CollegeInfo> listAll() {
        Example example = new Example(CollegeInfo.class);
        example.createCriteria().andEqualTo("state", State.NORMAL.getState());
        return collegeInfoMapper.selectByExample(example);
    }

    @Override
    public Page<CollegeInfo> selectByRecordForPage(CollegeInfo record, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(CollegeInfo.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo, pageSize);
        example.setOrderByClause(orderByClause);

        if (record.getParentOrgId() != null) {
            criteria.andEqualTo("parentOrgId", record.getParentOrgId());
        }
        if (record.getType() != null) {
            criteria.andEqualTo("type", record.getType());
        }
        if (record.getUniversityId() != null) {
            criteria.andEqualTo("universityId", record.getUniversityId());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state", record.getState());
        }

        List<CollegeInfo> list = collegeInfoMapper.selectByExample(example);
        PageInfo<CollegeInfo> pageInfo = new PageInfo<CollegeInfo>(list);
        Page<CollegeInfo> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int) pageInfo.getTotal());
        return page;
    }

    @Override
    public List<CollegeInfo> selectByRecordForList(CollegeInfo record) {
        Example example = new Example(CollegeInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if (record.getParentOrgId() != null) {
            criteria.andEqualTo("parentOrgId", record.getParentOrgId());
        }
        if (record.getType() != null) {
            criteria.andEqualTo("type", record.getType());
        }
        if (record.getUniversityId() != null) {
            criteria.andEqualTo("universityId", record.getUniversityId());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state", record.getState());
        }

        return collegeInfoMapper.selectByExample(example);
    }

    @Override
    public Page<CollegeInfo> selectByParamsForPage(Long parentOrgId, Byte type, Long universityId, String state, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(CollegeInfo.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo, pageSize);
        example.setOrderByClause(orderByClause);

        if (parentOrgId != null) {
            criteria.andEqualTo("parentOrgId", parentOrgId);
        }
        if (type != null) {
            criteria.andEqualTo("type", type);
        }
        if (universityId != null) {
            criteria.andEqualTo("universityId", universityId);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }

        List<CollegeInfo> list = collegeInfoMapper.selectByExample(example);
        PageInfo<CollegeInfo> pageInfo = new PageInfo<CollegeInfo>(list);
        Page<CollegeInfo> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int) pageInfo.getTotal());
        return page;
    }

    @Override
    public List<CollegeInfo> selectByParamsForList(Long parentOrgId, Byte type, Long universityId, String state) {
        Example example = new Example(CollegeInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if (parentOrgId != null) {
            criteria.andEqualTo("parentOrgId", parentOrgId);
        }
        if (type != null) {
            criteria.andEqualTo("type", type);
        }
        if (universityId != null) {
            criteria.andEqualTo("universityId", universityId);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }

        return collegeInfoMapper.selectByExample(example);
    }

}
