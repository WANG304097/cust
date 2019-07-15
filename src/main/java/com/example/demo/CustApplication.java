package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.situ.crm.*.controller,com.situ.crm.*.service"})
@MapperScan(value = "com.situ.crm.*.mapper")
public class CustApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustApplication.class, args);
	}

}
