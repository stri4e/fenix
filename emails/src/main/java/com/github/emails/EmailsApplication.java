package com.github.emails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EmailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailsApplication.class, args);
	}

}
