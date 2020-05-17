package com.github.users.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UsersCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersCenterApplication.class, args);
	}

}
