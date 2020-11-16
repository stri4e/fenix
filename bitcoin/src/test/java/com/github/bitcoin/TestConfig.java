package com.github.bitcoin;

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

        properties.setProperty("bitcoin.wallet.name", "test_wallet");
        properties.setProperty("bitcoin.request.period", "100000");
        properties.setProperty("bitcoin.api.url", "http://localhost:2222");
        properties.setProperty("swagger.enabled", "false");

        pspc.setProperties(properties);
        return pspc;
    }

}
