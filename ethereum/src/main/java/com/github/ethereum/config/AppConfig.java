package com.github.ethereum.config;

import com.github.facade.ethrereum.FacadeEthereum;
import com.github.facade.ethrereum.IFacadeEthereum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableCaching
@EntityScan(value = "com.github.ethereum.entity")
@EnableJpaRepositories(basePackages = "com.github.ethereum.repository")
public class AppConfig {

    @Value(value = "${ethereum.api.url}")
    private String ethereumApiUrl;

    @Value(value = "${ethereum.api.time}")
    private Integer time;

    @Bean
    public IFacadeEthereum ethereum() {
        return new FacadeEthereum(this.ethereumApiUrl, this.time);
    }

}
