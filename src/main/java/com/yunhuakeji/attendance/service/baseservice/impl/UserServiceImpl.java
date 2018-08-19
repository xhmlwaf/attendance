package com.yunhuakeji.attendance.service.baseservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.UserMapper;
import com.yunhuakeji.attendance.dao.basedao.model.StatStudentByGender;
import com.yunhuakeji.attendance.dao.basedao.model.StudentKeysInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.enums.State;
import com.yunhuakeji.attendance.enums.UserType;
import com.yunhuakeji.attendance.service.baseservice.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectByPrimaryKeyList(List<Long> ids) {
        Example example = new Example(User.class);
        example.createCriteria().andIn("userId", ids);
        return userMapper.selectByExample(example);
    }

    @Override
    public Page<User> selectByRecordForPage(User record, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo, pageSize);
        example.setOrderByClause(orderByClause);

        if (record.getUniversityId() != null) {
            criteria.andEqualTo("universityId", record.getUniversityId());
        }
        if (record.getUserType() != null) {
            criteria.andEqualTo("userType", record.getUserType());
        }
        if (record.getGender() != null) {
            criteria.andEqualTo("gender", record.getGender());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state", record.getState());
        }

        List<User> list = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        Page<User> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int) pageInfo.getTotal());
        return page;
    }

    @Override
    public List<User> selectByRecordForList(User record) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if (record.getUniversityId() != null) {
            criteria.andEqualTo("universityId", record.getUniversityId());
        }
        if (record.getUserType() != null) {
            criteria.andEqualTo("userType", record.getUserType());
        }
        if (record.getGender() != null) {
            criteria.andEqualTo("gender", record.getGender());
        }
        if (record.getState() != null) {
            criteria.andEqualTo("state", record.getState());
        }

        return userMapper.selectByExample(example);
    }

    @Override
    public Page<User> selectByParamsForPage(Long universityId, Byte userType, Byte gender, String state, int pageNo, int pageSize, String orderByClause) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        PageHelper.startPage(pageNo, pageSize);
        example.setOrderByClause(orderByClause);

        if (universityId != null) {
            criteria.andEqualTo("universityId", universityId);
        }
        if (userType != null) {
            criteria.andEqualTo("userType", userType);
        }
        if (gender != null) {
            criteria.andEqualTo("gender", gender);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }

        List<User> list = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        Page<User> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult(list);
        page.setTotalCount((int) pageInfo.getTotal());
        return page;
    }

    @Override
    public List<User> selectByParamsForList(Long universityId, Byte userType, Byte gender, String state) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if (universityId != null) {
            criteria.andEqualTo("universityId", universityId);
        }
        if (userType != null) {
            criteria.andEqualTo("userType", userType);
        }
        if (gender != null) {
            criteria.andEqualTo("gender", gender);
        }
        if (state != null) {
            criteria.andEqualTo("state", state);
        }

        return userMapper.selectByExample(example);
    }

    @Override
    public PageInfo<User> getStudentForPage(String nameOrCode, Integer pageNo, Integer pageSize) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(nameOrCode)) {
            criteria.andLike("userName", "%"+nameOrCode+"%");
        }
        criteria.andEqualTo("userType", UserType.STUDENT.getType());
        criteria.andEqualTo("state", State.NORMAL.getState());

        Example.Criteria criteria1 = example.createCriteria();
        if (!StringUtils.isEmpty(nameOrCode)) {
            criteria1.andLike("code", "%"+nameOrCode+"%");
        }
        criteria1.andEqualTo("userType", UserType.STUDENT.getType());
        criteria1.andEqualTo("state", State.NORMAL.getState());

        if (!StringUtils.isEmpty(nameOrCode)) {
            example.or(criteria1);
        }

        PageHelper.startPage(pageNo, pageSize);
        List<User> userList = userMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(userList);

        return pageInfo;
    }

    @Override
    public PageInfo<StudentKeysInfo> getStudentForPageByClassIdsAndBuildingId(List<Long> classIds, Long buildingId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> userList = userMapper.getStudentForListByClassIdsAndBuildingId(classIds, buildingId);
        PageInfo pageInfo = new PageInfo(userList);

        return pageInfo;
    }

    @Override
    public PageInfo<StudentKeysInfo> getStudentForPageByNameOrCode(String nameOrCode, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> userList = userMapper.getStudentForListByNameOrCode(nameOrCode);
        PageInfo pageInfo = new PageInfo(userList);
        return pageInfo;
    }


}
