package com.yunhuakeji.attendance.service.bizservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.bizdao.InstructorClockMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClock;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClockCountStat;
import com.yunhuakeji.attendance.service.bizservice.InstructorClockService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class InstructorClockServiceImpl implements InstructorClockService {

    @Autowired
    private InstructorClockMapper instructorClockMapper;

    @Override
    public int statByInstructor(long instructorId) {
        Example example = new Example(InstructorClock.class);
        example.createCriteria().andEqualTo("instructorId", instructorId);
        return instructorClockMapper.selectCountByExample(example);
    }

    @Override
    public List<InstructorClock> list(long instructorId, long clockDate) {
        Example example = new Example(InstructorClock.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("instructorId", instructorId);
        criteria.andEqualTo("statDate", clockDate);
        return instructorClockMapper.selectByExample(example);
    }

    @Override
    public List<InstructorClock> list(long instructorId, int year, int month) {
        Example example = new Example(InstructorClock.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("instructorId", instructorId);
        long startDay = DateUtil.getYearMonthStartDay(year, month);
        long endDay = DateUtil.getYearMonthEndDay(year, month);
        criteria.andBetween("statDate", startDay, endDay);
        return instructorClockMapper.selectByExample(example);
    }

    @Override
    public int save(InstructorClock instructorClock) {
        return instructorClockMapper.insertSelective(instructorClock);
    }

    @Override
    public List<InstructorClockCountStat> instructorClockCountStatByIds(List<Long> instructorIds) {
        return instructorClockMapper.instructorClockCountStatByIds(instructorIds);
    }

    @Override
    public PageInfo<InstructorClock> page(Long instructorId, int pageNo, int pageSize) {
        Example example = new Example(InstructorClock.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("instructorId", instructorId);
        example.setOrderByClause("CLOCK_TIME");
        PageHelper.startPage(pageNo,pageSize);
        List<InstructorClock> instructorClockList = instructorClockMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(instructorClockList);
        return pageInfo;
    }
}
