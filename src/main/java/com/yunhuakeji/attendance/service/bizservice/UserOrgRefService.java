package com.yunhuakeji.attendance.service.bizservice;

import com.yunhuakeji.attendance.dao.bizdao.model.UserBuildingRef;
import com.yunhuakeji.attendance.dao.bizdao.model.UserOrgRef;

import java.util.List;

public interface UserOrgRefService {

    List<UserOrgRef> listByUserIds(List<Long> userIds);

    void batchInsert(List<Long> userIds, List<UserOrgRef> userOrgRefList);
}
