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

  public static void main(String[] args) {
    //System.out.println(hashPwd("123456"));
    System.out.println(checkPwd("123456","$2a$10$3LBhnYU1UqeOcqJCtvjiLuJMXDjl9IskxT1qmqSk8pK922hDD3Lee"));
  }
  //$2a$10$3LBhnYU1UqeOcqJCtvjiLuJMXDjl9IskxT1qmqSk8pK922hDD3Lee
  //$2a$10$qvzLv7q5oTvAkC6dbLE2iuhqxjEUBkZXbpolyBsWOdyZtbBYSDlX.


}
