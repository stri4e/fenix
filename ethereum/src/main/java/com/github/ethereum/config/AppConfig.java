package com.github.ethereum.config;

import com.github.wrapper.ethrereum.facade.IFacadeEthereum;
import com.github.wrapper.ethrereum.facade.impl.FacadeEthereum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.http.HttpService;

@Configuration
public class AppConfig {

    @Bean
    public IFacadeEthereum ethereum() {
        return new FacadeEthereum(HttpService.DEFAULT_URL);
    }

}
