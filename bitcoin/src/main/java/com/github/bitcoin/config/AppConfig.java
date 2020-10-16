package com.github.bitcoin.config;

import com.github.wrapper.bitcoin.facade.IFacadeBitcoin;
import com.github.wrapper.bitcoin.facade.impl.FacadeBitcoin;
import com.github.wrapper.bitcoin.utils.Network;
import com.github.wrapper.bitcoin.utils.WrapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Value(value = "app.wallet.name")
    private String walletName;

    @Value(value = "app.request.period")
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
