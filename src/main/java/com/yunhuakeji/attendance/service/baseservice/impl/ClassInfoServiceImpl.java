package com.yunhuakeji.attendance.service.baseservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.ClassInfoMapper;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.enums.State;
import com.yunhuakeji.attendance.service.baseservice.ClassInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Override
    public int insert(ClassInfo record) {
        return classInfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return classInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ClassInfo record) {
        return classInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ClassInfo selectByPrimaryKey(String id) {
        return classInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ClassInfo> selectByPrimaryKeyList(List<String> ids) {
        Example example = new Example(ClassInfo.class);
        example.createCriteria().andIn("id",ids);
        return classInfoMapper.selectByExample(example);
    }

    @Override
    public List<ClassInfo> listAll() {
        Example example = new Example(ClassInfo.class);
        example.createCriteria().andEqualTo("state", State.NORMAL);
        return classInfoMapper.selectByExample(example);
    }

    @Override
    public Page<ClassInfo> selectByRecordForPage(ClassInfo record, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(ClassInfo.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo,pageSize);
        example.setOrderByClause(orderByClause);

        if (record.getInstructorId() != null) {
            criteria.andEqualTo("instructorId",record.getInstructorId());
        }
        if (record.getMajorId() != null) {
            criteria.andEqualTo("majorId",record.getMajorId());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state",record.getState());
        }

        List<ClassInfo> list = classInfoMapper.selectByExample(example);
        PageInfo<ClassInfo> pageInfo = new PageInfo<ClassInfo>(list);
        Page<ClassInfo> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int)pageInfo.getTotal());
        return page;
    }

    @Override
    public List<ClassInfo> selectByRecordForList(ClassInfo record) {
        Example example = new Example(ClassInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if (record.getInstructorId() != null) {
            criteria.andEqualTo("instructorId",record.getInstructorId());
        }
        if (record.getMajorId() != null) {
            criteria.andEqualTo("majorId",record.getMajorId());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state",record.getState());
        }

        return classInfoMapper.selectByExample(example);
    }

    @Override
    public Page<ClassInfo> selectByParamsForPage(Long instructorId,Long majorId,String state, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(ClassInfo.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo,pageSize);
        example.setOrderByClause(orderByClause);

        if (instructorId != null) {
             criteria.andEqualTo("instructorId",instructorId);
        }
        if (majorId != null) {
             criteria.andEqualTo("majorId",majorId);
        }
        if (state != null) {
             criteria.andEqualTo("state",state);
        }

        List<ClassInfo> list = classInfoMapper.selectByExample(example);
        PageInfo<ClassInfo> pageInfo = new PageInfo<ClassInfo>(list);
        Page<ClassInfo> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int)pageInfo.getTotal());
        return page;
    }

    @Override
    public List<ClassInfo> selectByParamsForList(Long instructorId,Long majorId,String state) {
        Example example = new Example(ClassInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if (instructorId != null) {
            criteria.andEqualTo("instructorId",instructorId);
        }
        if (majorId != null) {
            criteria.andEqualTo("majorId",majorId);
        }
        if (state != null) {
            criteria.andEqualTo("state",state);
        }

        return classInfoMapper.selectByExample(example);
    }

 }
