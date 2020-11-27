package com.github.advertising;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class AdvertisingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvertisingApplication.class, args);
	}

}
