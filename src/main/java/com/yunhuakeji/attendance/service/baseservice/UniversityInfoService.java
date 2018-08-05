package com.yunhuakeji.attendance.service.baseservice;

import com.yunhuakeji.attendance.dao.basedao.model.UniversityInfo;

import java.util.List;

public interface UniversityInfoService {

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(UniversityInfo record);

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
    int updateByPrimaryKeySelective(UniversityInfo record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    UniversityInfo selectByPrimaryKey(String id);

    /**
     * 根据ID列表查询
     * @param ids
     * @return
     */
    List<UniversityInfo> selectByPrimaryKeyList(List<String> ids);

}