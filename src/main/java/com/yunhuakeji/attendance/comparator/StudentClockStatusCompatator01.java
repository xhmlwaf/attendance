package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockStatusDO;

import java.io.Serializable;
import java.util.Comparator;

//continuousStayoutDays
public class StudentClockStatusCompatator01 implements Comparator<StudentClockStatusDO>,Serializable {
    @Override
    public int compare(StudentClockStatusDO o1, StudentClockStatusDO o2) {
        return o1.getLxStayOut()-o2.getLxStayOut();
    }
}
