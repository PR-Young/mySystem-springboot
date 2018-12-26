package com.example.mySystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mySystem.dao") // 将项目中对应的mapper类的路径加进来就可以了
public class MySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySystemApplication.class, args);
	}
}
