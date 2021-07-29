package com.fse.usecase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FseEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FseEurekaClientApplication.class, args);
	}

}
