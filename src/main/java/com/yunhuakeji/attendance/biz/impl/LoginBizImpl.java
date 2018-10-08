package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.aspect.RequestLog;
import com.yunhuakeji.attendance.biz.LoginBiz;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.constants.ConfigConstants;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserOrg;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dto.request.AdminLoginReqDTO;
import com.yunhuakeji.attendance.dto.response.AdminLoginRspDTO;
import com.yunhuakeji.attendance.enums.RoleType;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.baseservice.UserOrgService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.AccountService;
import com.yunhuakeji.attendance.service.bizservice.RedisService;
import com.yunhuakeji.attendance.util.PasswordUtil;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginBizImpl implements LoginBiz {

  private static final Logger logger = LoggerFactory.getLogger(RequestLog.class);

  @Autowired
  private AccountService accountService;

  @Autowired
  private UserService userService;

  @Autowired
  private RedisService redisService;

  @Autowired
  private UserOrgService userOrgService;

  @Autowired
  private OrgCacheService orgCacheService;

  @Override
  public Result<AdminLoginRspDTO> login(AdminLoginReqDTO reqDTO) {
    String username = reqDTO.getUsername();
    String password = reqDTO.getPassword();
    long userId = 0;
    try {
      if ("admin".equals(username)) {
        userId = ConfigConstants.ADMIN_USER_ID;
      } else {
        userId = Long.parseLong(username);
      }
    } catch (NumberFormatException e) {
      logger.error(e.getMessage(), e);
      throw new BusinessException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
    }
    Account account = accountService.getAccountByUserId(userId);
    if (account == null) {
      logger.warn("not exsit!username:{}", username);
      throw new BusinessException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
    }
    int sys = reqDTO.getSys();
    if (sys == 1) {
      if (account.getRoleType() != RoleType.SecondaryCollegeAdmin.getType() &&
          account.getRoleType() != RoleType.StudentsAffairsAdmin.getType()) {
        throw new BusinessException(ErrorCode.NO_AUTH);
      }
    } else {
      if (userId != ConfigConstants.ADMIN_USER_ID) {
        throw new BusinessException(ErrorCode.NO_AUTH);
      }
    }
    boolean check = PasswordUtil.checkPwd(password, account.getPassword());
    if (!check) {
      logger.warn("password error.");
      throw new BusinessException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
    }
    User user = userService.selectByPrimaryKey(userId);
    AdminLoginRspDTO dto = new AdminLoginRspDTO();
    if (user != null) {
      dto.setUserId(userId);
      dto.setName(user.getUserName());
      dto.setProfilePhoto(user.getHeadPortraitPath());
    }
    if (userId == ConfigConstants.ADMIN_USER_ID) {
      dto.setOrgName(ConfigConstants.ADMIN_ORG_NAME);
    } else {
      List<UserOrg> userOrgList = userOrgService.selectByUserId(userId);
      if (CollectionUtils.isNotEmpty(userOrgList)) {
        long orgId = userOrgList.get(0).getOrgId();
        CollegeInfo collegeInfo = orgCacheService.getCollegeInfoMap().get(orgId);
        if (collegeInfo != null) {
          dto.setOrgName(collegeInfo.getName());
        }
      }
    }
    String token = UUID.randomUUID().toString();
    dto.setToken(token);
    redisService.setForTimeCustom(token, userId, ConfigConstants.TOKEN_TTL, TimeUnit.MINUTES);
    return Result.success(dto);
  }
}
