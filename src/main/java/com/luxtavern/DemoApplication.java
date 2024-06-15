package com.luxtavern;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude=SecurityAutoConfiguration.class)
@EnableWebMvc
@EnableJpaRepositories
@ComponentScan(basePackages = "com.luxtavern")
public class DemoApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
