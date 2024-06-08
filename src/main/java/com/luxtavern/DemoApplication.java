package com.luxtavern;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.luxtavern")
public class DemoApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
