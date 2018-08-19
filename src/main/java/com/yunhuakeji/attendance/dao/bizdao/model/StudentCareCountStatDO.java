package com.yunhuakeji.attendance.dao.bizdao.model;

/**
 * 统计学生被关怀次数
 */
public class StudentCareCountStatDO {

    private Long studentId;
    private Integer statCount;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getStatCount() {
        return statCount;
    }

    public void setStatCount(Integer statCount) {
        this.statCount = statCount;
    }
}
