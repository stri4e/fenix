package com.github.bitcoin.network;

import com.github.bitcoin.services.INetworkService;
import com.github.facade.bitcoin.IFacadeBitcoin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Networking implements ApplicationRunner {

    private final IFacadeBitcoin facadeBitcoin;

    private final INetworkService networkService;

    @Value(value = "${bitcoin.api.url}")
    private String apiUrl;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.facadeBitcoin.networking();
        this.facadeBitcoin.addBlockListener(
                this.networkService.findLastHeight(this.apiUrl),
                this.apiUrl,
                this.networkService::handlerBlock
        );
    }
}
