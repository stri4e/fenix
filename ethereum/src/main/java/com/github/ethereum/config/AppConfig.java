package com.github.ethereum.config;

import com.github.wrapper.ethrereum.facade.IFacadeEthereum;
import com.github.wrapper.ethrereum.facade.impl.FacadeEthereum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value(value = "${ethereum.api.url}")
    private String ethereumApiUrl;

    @Bean
    public IFacadeEthereum ethereum() {
        return new FacadeEthereum(this.ethereumApiUrl);
    }

}
