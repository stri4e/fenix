package com.github.ethereum.network;

import com.github.ethereum.services.INetworkService;
import com.github.wrapper.ethrereum.facade.IFacadeEthereum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Networking implements ApplicationRunner {

    private final INetworkService networkService;

    private final IFacadeEthereum facadeEthereum;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.facadeEthereum.blockTracker(
                this.networkService::findBlockNumber,
                this.networkService::incomingTransaction,
                this.networkService::outgoingTransaction,
                this.networkService::incomingContract,
                this.networkService::outgoingTransaction,
                this.networkService::accountAddresses,
                this.networkService::contractsAddresses,
                this.networkService::createOrUpdateFee,
                this.networkService::updateBlockNumber,
                err -> log.warn("Enter: {}", err.getMessage())
        );
    }

}
