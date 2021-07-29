package com.fse.usecase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.fse.usecase.repository")
@EnableDiscoveryClient
//@EnableHystrix
public class CompanyRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyRegistrationApplication.class, args);
	}

}
