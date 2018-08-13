package com.yunhuakeji.attendance.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class DeleteAccountReqDTO {

    @ApiModelProperty(value = "角色类型，1:二级院校管理员,2:宿舍管理员,3:学生处管理员")
    private byte roleType;
    @ApiModelProperty(value = "用户列表")
    private List<Long> userIds;

    public byte getRoleType() {
        return roleType;
    }

    public void setRoleType(byte roleType) {
        this.roleType = roleType;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}
