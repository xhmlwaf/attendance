package com.yunhuakeji.attendance.service.baseservice.impl;

import com.yunhuakeji.attendance.dao.basedao.StaffInfoMapper;
import com.yunhuakeji.attendance.dao.basedao.model.StaffInfo;
import com.yunhuakeji.attendance.service.baseservice.StaffInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class StaffInfoServiceImpl implements StaffInfoService {

    @Autowired
    private StaffInfoMapper staffInfoMapper;

    @Override
    public int insert(StaffInfo record) {
        return staffInfoMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return staffInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(StaffInfo record) {
        return staffInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public StaffInfo selectByPrimaryKey(String id) {
        return staffInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StaffInfo> selectByPrimaryKeyList(List<String> ids) {
        Example example = new Example(StaffInfo.class);
        example.createCriteria().andIn("id",ids);
        return staffInfoMapper.selectByExample(example);
    }



 }
