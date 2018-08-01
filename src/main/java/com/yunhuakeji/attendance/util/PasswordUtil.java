package com.yunhuakeji.attendance.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 密码工具
 */
public class PasswordUtil {

  public static String hashPwd(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public static boolean checkPwd(String candidate, String hashed) {
    return BCrypt.checkpw(candidate, hashed);
  }
}
