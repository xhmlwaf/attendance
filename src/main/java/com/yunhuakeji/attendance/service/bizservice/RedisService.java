package com.yunhuakeji.attendance.service.bizservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

  @Autowired
  private RedisTemplate redisTemplate;

  /**
   * 如果 key 存在则覆盖,并返回旧值. 如果不存在,返回null 并添加
   *
   * @param key
   * @param value
   * @return
   */
  public String getAndSet(String key, Object value) {
    return (String) redisTemplate.opsForValue().getAndSet(key, value);
  }

  /**
   * 给一个指定的 key 值附加过期时间
   *
   * @param key
   * @param time
   * @param type
   * @return
   */
  public boolean expire(String key, long time, TimeUnit type) {
    return redisTemplate.boundValueOps(key).expire(time, type);
  }

  /**
   * 获取 String 类型 key-value
   *
   * @param key
   * @return
   */
  public Object get(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  /**
   * 设置 String 类型 key-value 并添加过期时间 (分钟单位)
   *
   * @param key
   * @param value
   * @param time  过期时间,分钟单位
   */
  public void setForTimeCustom(String key, Object value, long time, TimeUnit type) {
    redisTemplate.opsForValue().set(key, value, time, type);
  }
}
