package com.yunhuakeji.attendance.service.baseservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.DormitoryUserMapper;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryUser;
import com.yunhuakeji.attendance.service.baseservice.DormitoryUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class DormitoryUserServiceImpl implements DormitoryUserService {

    @Autowired
    private DormitoryUserMapper dormitoryUserMapper;

    @Override
    public int insert(DormitoryUser record) {
        return dormitoryUserMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return dormitoryUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DormitoryUser record) {
        return dormitoryUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public DormitoryUser selectByPrimaryKey(String id) {
        return dormitoryUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DormitoryUser> selectByPrimaryKeyList(List<String> ids) {
        Example example = new Example(DormitoryUser.class);
        example.createCriteria().andIn("id", ids);
        return dormitoryUserMapper.selectByExample(example);
    }

    @Override
    public Page<DormitoryUser> selectByRecordForPage(DormitoryUser record, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(DormitoryUser.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo, pageSize);
        example.setOrderByClause(orderByClause);

        if (record.getDormitoryId() != null) {
            criteria.andEqualTo("dormitoryId", record.getDormitoryId());
        }
        if (record.getUserId() != null) {
            criteria.andEqualTo("userId", record.getUserId());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state", record.getState());
        }

        List<DormitoryUser> list = dormitoryUserMapper.selectByExample(example);
        PageInfo<DormitoryUser> pageInfo = new PageInfo<DormitoryUser>(list);
        Page<DormitoryUser> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int) pageInfo.getTotal());
        return page;
    }

    @Override
    public List<DormitoryUser> selectByRecordForList(DormitoryUser record) {
        Example example = new Example(DormitoryUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (record.getDormitoryId() != null) {
            criteria.andEqualTo("dormitoryId", record.getDormitoryId());
        }
        if (record.getUserId() != null) {
            criteria.andEqualTo("userId", record.getUserId());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state", record.getState());
        }

        return dormitoryUserMapper.selectByExample(example);
    }

    @Override
    public Page<DormitoryUser> selectByParamsForPage(Long dormitoryId, Long userId, String state, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(DormitoryUser.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo, pageSize);
        example.setOrderByClause(orderByClause);

        if (dormitoryId != null) {
            criteria.andEqualTo("dormitoryId", dormitoryId);
        }
        if (userId != null) {
            criteria.andEqualTo("userId", userId);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }

        List<DormitoryUser> list = dormitoryUserMapper.selectByExample(example);
        PageInfo<DormitoryUser> pageInfo = new PageInfo<DormitoryUser>(list);
        Page<DormitoryUser> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int) pageInfo.getTotal());
        return page;
    }

    @Override
    public List<DormitoryUser> selectByParamsForList(Long dormitoryId, Long userId, String state) {
        Example example = new Example(DormitoryUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (dormitoryId != null) {
            criteria.andEqualTo("dormitoryId", dormitoryId);
        }
        if (userId != null) {
            criteria.andEqualTo("userId", userId);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }

        return dormitoryUserMapper.selectByExample(example);
    }

}
