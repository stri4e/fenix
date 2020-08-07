package com.github.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Properties;

@Configuration
public class TestConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() throws Exception {
        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();
        properties.setProperty("swagger.enabled", "false");
        properties.setProperty("app.user.secret.key", "205d36838e32d2905b2b65988a201aec50c4a29795c71acdb32f60768a1dd123");
        pspc.setProperties(properties);
        return pspc;
    }

}
