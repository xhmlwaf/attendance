package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.model.User;

import java.util.List;

public interface UserService {

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(String id);

    /**
     * 根据ID列表查询
     *
     * @param ids
     * @return
     */
    List<User> selectByPrimaryKeyList(List<String> ids);

    /**
     * 分页查询
     *
     * @param record
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<User> selectByRecordForPage(User record, int pageNo, int pageSize, String orderByClause);

    /**
     * 列表查询
     *
     * @param record
     * @return
     */
    List<User> selectByRecordForList(User record);

    /**
     * 根据参数分页查询
     *
     * @param universityId
     * @param userType
     * @param gendor
     * @param state
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<User> selectByParamsForPage(Long universityId, Short userType, Short gendor, String state, int pageNo, int pageSize, String orderByClause);

    /**
     * 根据参数列表查询
     *
     * @param universityId
     * @param userType
     * @param gendor
     * @param state
     * @return
     */
    List<User> selectByParamsForList(Long universityId, Short userType, Short gendor, String state);

}