package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.dao.basedao.model.StaffInfo;

import java.util.List;

public interface StaffInfoService {

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(StaffInfo record);


    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(StaffInfo record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    StaffInfo selectByPrimaryKey(String id);

    /**
     * 根据ID列表查询
     * @param ids
     * @return
     */
    List<StaffInfo> selectByPrimaryKeyList(List<String> ids);

}