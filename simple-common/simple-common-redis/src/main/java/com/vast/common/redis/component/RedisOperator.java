package com.vast.common.redis.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 使用redisTemplate的操作实现类
 */
@Component
public class RedisOperator {

	@Autowired
	private RedisTemplate redisTemplate;

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}


	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	public void del(String key) {
		redisTemplate.delete(key);
	}

	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public void set(String key, String value, long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}

	public String get(String key) {
		return (String)redisTemplate.opsForValue().get(key);
	}

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

	public void hashSet(String key, String field, Object value) {
		redisTemplate.opsForHash().put(key, field, value);
	}

	public boolean hashExists(String key, String field){
		return redisTemplate.opsForHash().hasKey(key, field);
	}
	public Object hashGet(String key, String field) {
		return redisTemplate.opsForHash().get(key, field);
	}

	public void hashDel(String key, Object... fields) {
		redisTemplate.opsForHash().delete(key, fields);
	}

	public Map<Object, Object> hashGetAll(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	public List<String> getValues(Collection<String> keys){
		return redisTemplate.opsForValue().multiGet(keys);
	}
}