package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockStatusDO;

import java.io.Serializable;
import java.util.Comparator;

//continuousStayoutLateDays
public class StudentClockStatusCompatator02 implements Comparator<StudentClockStatusDO>,Serializable {
    @Override
    public int compare(StudentClockStatusDO o1, StudentClockStatusDO o2) {

        return o1.getLxStayOutLate()-o2.getLxStayOutLate();
    }
}
