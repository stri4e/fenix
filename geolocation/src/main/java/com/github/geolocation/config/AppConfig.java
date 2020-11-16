package com.github.geolocation.config;

import com.maxmind.geoip2.DatabaseReader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final ResourceLoader resourceLoader;

    @Bean
    public DatabaseReader databaseReader() throws IOException {
        Resource resource = this.resourceLoader.getResource("classpath:GeoLite2-City.mmdb");
        File database = resource.getFile();
        return new DatabaseReader.Builder(database).build();
    }

}
