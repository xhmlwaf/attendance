package com.yunhuakeji.attendance.service.baseservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.MajorInfoMapper;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.enums.State;
import com.yunhuakeji.attendance.service.baseservice.MajorInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class MajorInfoServiceImpl implements MajorInfoService {

    @Autowired
    private MajorInfoMapper majorInfoMapper;

    @Override
    public int insert(MajorInfo record) {
        return majorInfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return majorInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MajorInfo record) {
        return majorInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public MajorInfo selectByPrimaryKey(String id) {
        return majorInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MajorInfo> selectByPrimaryKeyList(List<String> ids) {
        Example example = new Example(MajorInfo.class);
        example.createCriteria().andIn("id", ids);
        return majorInfoMapper.selectByExample(example);
    }

    @Override
    public List<MajorInfo> listAll() {
        Example example = new Example(MajorInfo.class);
        example.createCriteria().andEqualTo("state", State.NORMAL.getState());
        return majorInfoMapper.selectByExample(example);
    }

    @Override
    public Page<MajorInfo> selectByRecordForPage(MajorInfo record, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(MajorInfo.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo, pageSize);
        example.setOrderByClause(orderByClause);

        if (record.getOrgId() != null) {
            criteria.andEqualTo("orgId", record.getOrgId());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state", record.getState());
        }

        List<MajorInfo> list = majorInfoMapper.selectByExample(example);
        PageInfo<MajorInfo> pageInfo = new PageInfo<MajorInfo>(list);
        Page<MajorInfo> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int) pageInfo.getTotal());
        return page;
    }

    @Override
    public List<MajorInfo> selectByRecordForList(MajorInfo record) {
        Example example = new Example(MajorInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if (record.getOrgId() != null) {
            criteria.andEqualTo("orgId", record.getOrgId());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state", record.getState());
        }

        return majorInfoMapper.selectByExample(example);
    }

    @Override
    public Page<MajorInfo> selectByParamsForPage(Long orgId, String state, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(MajorInfo.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo, pageSize);
        example.setOrderByClause(orderByClause);

        if (orgId != null) {
            criteria.andEqualTo("orgId", orgId);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }

        List<MajorInfo> list = majorInfoMapper.selectByExample(example);
        PageInfo<MajorInfo> pageInfo = new PageInfo<MajorInfo>(list);
        Page<MajorInfo> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int) pageInfo.getTotal());
        return page;
    }

    @Override
    public List<MajorInfo> selectByParamsForList(Long orgId, String state) {
        Example example = new Example(MajorInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if (orgId != null) {
            criteria.andEqualTo("orgId", orgId);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }

        return majorInfoMapper.selectByExample(example);
    }

}
