package com.github.bitcoin.config;

import com.github.wrapper.bitcoin.facade.IFacadeBitcoin;
import com.github.wrapper.bitcoin.facade.impl.FacadeBitcoin;
import com.github.wrapper.bitcoin.utils.Network;
import com.github.wrapper.bitcoin.utils.WrapUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public IFacadeBitcoin facadeBitcoin() {
        return new FacadeBitcoin(
                Network.MAIN,
                WrapUtils.DETERMINISTIC_PATH_MAIN,
                ".",
                "btc_wallet",
                10
        );
    }

}
