package com.yunhuakeji.attendance.service.baseservice.impl;

import com.yunhuakeji.attendance.dao.basedao.StudentInfoMapper;
import com.yunhuakeji.attendance.dao.basedao.model.StudentInfo;
import com.yunhuakeji.attendance.service.baseservice.StudentInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    public int insert(StudentInfo record) {
        return studentInfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return studentInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StudentInfo record) {
        return studentInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public StudentInfo selectByPrimaryKey(String id) {
        return studentInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StudentInfo> selectByPrimaryKeyList(List<String> ids) {
        Example example = new Example(StudentInfo.class);
        example.createCriteria().andIn("id",ids);
        return studentInfoMapper.selectByExample(example);
    }

 }
