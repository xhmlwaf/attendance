package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.aspect.RequestLog;
import com.yunhuakeji.attendance.biz.LoginBiz;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.dto.request.AdminLoginReqDTO;
import com.yunhuakeji.attendance.dto.response.AdminLoginRspDTO;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.AccountService;
import com.yunhuakeji.attendance.util.PasswordUtil;

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

  @Override
  public Result<AdminLoginRspDTO> login(AdminLoginReqDTO reqDTO) {
    String username = reqDTO.getUsername();
    String password = reqDTO.getPassword();
    long userId = 0;
    try {
      userId = Long.parseLong(username);
    } catch (NumberFormatException e) {
      logger.error("用户名格式错误.", e);
      throw new BusinessException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
    }
    Account account = accountService.getAccountByUserId(userId);
    if (account == null) {
      logger.warn("用户不存在.username:{}", username);
      throw new BusinessException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
    }
    boolean check = PasswordUtil.checkPwd(password, account.getPassword());
    if (!check) {
      logger.warn("密码错误.");
      throw new BusinessException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
    }
    User user = userService.selectByPrimaryKey(userId);
    if (user == null) {
      logger.warn("用户不存在.username:{}", username);
      throw new BusinessException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
    }
    AdminLoginRspDTO dto = new AdminLoginRspDTO();
    dto.setUserId(userId);
    dto.setName(user.getUserName());
    dto.setProfilePhoto(user.getHeadPortraitPath());
    //TODO token
    return Result.success(dto);
  }
}
