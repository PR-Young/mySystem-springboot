package com.example.mySystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mySystem.util.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySystemApplicationTests {

	@Autowired
	RedisUtil redisUtil;

	@Test
	public void contextLoads() {
	}

	@Test
	public void setRedis() {
		redisUtil.set("test", "测试 ");

		String a = redisUtil.get("test");
		System.out.println(a);
	}

	@Test
	public void setRedisTime() {
		long time = 60;
		redisUtil.set("time", "111eee测试", time);
	}
}
