package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.dao.bizdao.StudentDeviceRefMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentDeviceRef;
import com.yunhuakeji.attendance.service.bizservice.StudentDeviceRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class StudentDeviceRefServiceImpl implements StudentDeviceRefService {

    @Autowired
    private StudentDeviceRefMapper studentDeviceRefMapper;

    @Override
    public List<StudentDeviceRef> list(Long studentId) {
        Example example = new Example(StudentDeviceRef.class);
        example.createCriteria().andEqualTo("studentId", studentId);
        return studentDeviceRefMapper.selectByExample(example);
    }
}
