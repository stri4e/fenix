package com.github.payments.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Properties;

@Configuration
public class TestConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() throws Exception {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();

        properties.setProperty("exchange.url", "http://localhost:2222");
        properties.setProperty("exchange.key", "9da421d7-33e4-4319-9288-0f503bd20c3d");
        properties.setProperty("swagger.enabled", "false");

        pspc.setProperties(properties);
        return pspc;
    }

}
