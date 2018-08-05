package com.yunhuakeji.attendance.service.baseservice.impl;

import com.yunhuakeji.attendance.dao.basedao.UniversityInfoMapper;
import com.yunhuakeji.attendance.dao.basedao.model.UniversityInfo;
import com.yunhuakeji.attendance.service.baseservice.UniversityInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class UniversityInfoServiceImpl implements UniversityInfoService {

    @Autowired
    private UniversityInfoMapper universityInfoMapper;

    @Override
    public int insert(UniversityInfo record) {
        return universityInfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return universityInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UniversityInfo record) {
        return universityInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UniversityInfo selectByPrimaryKey(String id) {
        return universityInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UniversityInfo> selectByPrimaryKeyList(List<String> ids) {
        Example example = new Example(UniversityInfo.class);
        example.createCriteria().andIn("id",ids);
        return universityInfoMapper.selectByExample(example);
    }

 }
