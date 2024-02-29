package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@SpringBootApplication
//@Configuration
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	@Profile("h2")
//	public DataSource dataSourceH2() {
//
//	}
//
//	@Bean
//	@Profile("mysql")
//	public DataSource dataSourceMysql() {
//
//	}
}
