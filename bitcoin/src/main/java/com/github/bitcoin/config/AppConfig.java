package com.github.bitcoin.config;

import com.github.facade.bitcoin.FacadeBitcoin;
import com.github.facade.bitcoin.IFacadeBitcoin;
import com.github.facade.bitcoin.utils.Network;
import com.github.facade.bitcoin.utils.WrapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(value = "com.github.bitcoin.entity")
@EnableJpaRepositories(basePackages = "com.github.bitcoin.repository")
public class AppConfig {

    @Value(value = "bitcoin.wallet.name")
    private String walletName;

    @Value(value = "bitcoin.request.period")
    private Integer requestPeriod;

    @Bean
    @Profile(value = {"default", "dev", "prod"})
    public IFacadeBitcoin facadeDefaultBitcoin() {
        return new FacadeBitcoin(
                Network.MAIN,
                WrapUtils.DETERMINISTIC_PATH_MAIN,
                ".",
                this.walletName,
                this.requestPeriod
        );
    }

    @Bean
    @Profile(value = {"test"})
    public IFacadeBitcoin facadeTestBitcoin() {
        return new FacadeBitcoin(
                Network.TEST,
                WrapUtils.DETERMINISTIC_PATH_TEST,
                ".",
                this.walletName,
                this.requestPeriod
        );
    }

}
