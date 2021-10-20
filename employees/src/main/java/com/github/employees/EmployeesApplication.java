package com.github.employees;

import com.github.employees.utils.JwtTokenProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EmployeesApplication implements ApplicationRunner {

    private final JwtTokenProvider jwtTokenProvider;

    public EmployeesApplication(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        this.jwtTokenProvider.accessToken(null, null, null);
    }
}
