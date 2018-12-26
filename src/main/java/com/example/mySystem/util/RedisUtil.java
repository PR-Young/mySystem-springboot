package com.example.mySystem.util;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * redis工具类
 * 
 * @author 1065569578@qq.com
 *
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtil {
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 批量删除对应的key
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0) {
			redisTemplate.delete(keys);
		}
	}

	/**
	 * 删除对应的key
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的key
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		Object result = null;
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		if (result == null) {
			return null;
		}
		return result.toString();
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 写入缓存，设置过期时间（s）
	 * 
	 * @param key
	 * @param value
	 * @param expireTime
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * hash写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean hmset(String key, Map<String, String> value) {
		boolean result = false;
		try {
			redisTemplate.opsForHash().putAll(key, value);
			result = true;
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * hash读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> hmget(String key) {
		Map<String, String> result = null;
		try {
			result = redisTemplate.opsForHash().entries(key);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
}
