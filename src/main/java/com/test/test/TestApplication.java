package com.test.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@MapperScan(basePackages = "com.test.test.dataobject.mapper") //在启动的时候去扫描这个包，不然mapper加载不到容器里面去
@EnableCaching  //这个注解是在springframe work 里面的
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
