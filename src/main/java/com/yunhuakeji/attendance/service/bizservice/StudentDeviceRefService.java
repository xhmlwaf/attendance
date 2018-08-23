package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentDeviceRef;

import java.util.List;

public interface StudentDeviceRefService {

    List<StudentDeviceRef> list(Long studentId);

    void deleteByStudentIds(List<Long> studentIds);

    void save(StudentDeviceRef studentDeviceRef);
}
