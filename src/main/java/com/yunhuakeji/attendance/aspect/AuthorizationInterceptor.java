package com.yunhuakeji.attendance.aspect;

import com.yunhuakeji.attendance.constants.ConfigConstants;
import com.yunhuakeji.attendance.constants.ErrorCode;
import com.yunhuakeji.attendance.dao.bizdao.model.Account;
import com.yunhuakeji.attendance.enums.RoleType;
import com.yunhuakeji.attendance.exception.BusinessException;
import com.yunhuakeji.attendance.interfaces.AdminAuth;
import com.yunhuakeji.attendance.interfaces.StatAuth;
import com.yunhuakeji.attendance.service.bizservice.AccountService;
import com.yunhuakeji.attendance.service.bizservice.RedisService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截
 * 使用方式：在控制器对应的方法上添加AdminAuth或StatAuth注解
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private RedisService redisService;

  @Autowired
  private AccountService accountService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //后台管理权限
    AdminAuth adminAuth = null;
    //统计分析权限
    StatAuth statAuth = null;
    if (handler instanceof HandlerMethod) {
      adminAuth = ((HandlerMethod) handler).getMethodAnnotation(AdminAuth.class);
      statAuth = ((HandlerMethod) handler).getMethodAnnotation(StatAuth.class);
    } else {
      return true;
    }

    //不验证token
    if (adminAuth == null && statAuth == null) {
      return true;
    }

    //获取用户凭证
    String token = request.getHeader(ConfigConstants.TOKEN);
    if (StringUtils.isBlank(token)) {
      throw new BusinessException(ErrorCode.TOKEN_IS_INVALID);
    }
    Object userIdObj = redisService.get(token);
    if (userIdObj == null) {
      throw new BusinessException(ErrorCode.TOKEN_IS_INVALID);
    }

    Long userId = (Long) userIdObj;
    if (adminAuth != null) {
      if (userId == ConfigConstants.ADMIN_USER_ID) {
        redisService.setForTimeCustom(token, userId, ConfigConstants.TOKEN_TTL, TimeUnit.MINUTES);
        return true;
      } else {
        throw new BusinessException(ErrorCode.TOKEN_IS_INVALID);
      }
    }
    if (statAuth != null) {
      Account account = accountService.getAccountByUserId(userId);
      if (account == null) {
        throw new BusinessException(ErrorCode.TOKEN_IS_INVALID);
      }
      if (RoleType.SecondaryCollegeAdmin.getType() == account.getRoleType() ||
          RoleType.StudentsAffairsAdmin.getType() == account.getRoleType()) {
        redisService.setForTimeCustom(token, userId, ConfigConstants.TOKEN_TTL, TimeUnit.MINUTES);
        return true;
      } else {
        throw new BusinessException(ErrorCode.TOKEN_IS_INVALID);
      }
    }
    return true;
  }
}
