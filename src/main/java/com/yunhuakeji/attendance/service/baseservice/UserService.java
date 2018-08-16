package com.yunhuakeji.attendance.service.baseservice;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.constants.Page;
import com.yunhuakeji.attendance.dao.basedao.model.StatStudentByGender;
import com.yunhuakeji.attendance.dao.basedao.model.StudentKeysInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import org.apache.ibatis.annotations.Param;

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
    User selectByPrimaryKey(Long id);

    /**
     * 根据ID列表查询
     *
     * @param ids
     * @return
     */
    List<User> selectByPrimaryKeyList(List<Long> ids);

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
     * @param gender
     * @param state
     * @param pageNo
     * @param pageSize
     * @param orderByClause
     * @return
     */
    Page<User> selectByParamsForPage(Long universityId, Byte userType, Byte gender, String state, int pageNo, int pageSize, String orderByClause);

    /**
     * 根据参数列表查询
     *
     * @param universityId
     * @param userType
     * @param gender
     * @param state
     * @return
     */
    List<User> selectByParamsForList(Long universityId, Byte userType, Byte gender, String state);


    PageInfo<User> getStudentForPage(String nameOrCode, Integer pageNo, Integer pageSize);

    PageInfo<StudentKeysInfo> getStudentForPageByClassIdsAndBuildingId(List<Long> classIds, Long buildingId, Integer pageNo, Integer pageSize);

    PageInfo<StudentKeysInfo> getStudentForPageByNameOrCode(String nameOrCode, Integer pageNo, Integer pageSize);


}