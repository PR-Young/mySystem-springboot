package com.example.mySystem.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 配置redis的template
 * 
 * @author 1065569578@qq.com
 *
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		// RedisCacheConfiguration redisCacheConfiguration =
		// RedisCacheConfiguration.defaultCacheConfig()
		// .entryTtl(Duration.ofHours(1)); // 设置缓存有效期一小时
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(redisCacheConfiguration).build();
	}

	/**
	 * RedisTemplate配置
	 * 
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		// 定义key序列化方式
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();// Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
		// 定义value的序列化方式
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		template.setKeySerializer(redisSerializer);
		template.setHashValueSerializer(redisSerializer);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	// @Bean
	// public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory
	// factory) {
	// RedisTemplate<String, String> redisTemplate = new RedisTemplate<String,
	// String>();
	// redisTemplate.setConnectionFactory(factory);
	// key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
	// 所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
	// 或者JdkSerializationRedisSerializer序列化方式;
	// RedisSerializer<String> redisSerializer = new StringRedisSerializer();//
	// Long类型不可以会出现异常信息;
	// redisTemplate.setKeySerializer(redisSerializer);
	// redisTemplate.setHashKeySerializer(redisSerializer);
	// return redisTemplate;
	// }
}
