package com.fse.usecase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.fse.usecase.repository")
@EnableDiscoveryClient
public class StockDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockDetailsApplication.class, args);
	}

}