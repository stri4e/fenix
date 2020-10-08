package com.github.payments.networks;

import com.github.payments.service.INetworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class Networking implements ApplicationRunner {

    private final INetworkService networkService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Executors.newScheduledThreadPool(BigInteger.ONE.intValue())
                .scheduleAtFixedRate(
                        this.networkService::rates,
                        BigInteger.ZERO.intValue(),
                        BigInteger.TEN.intValue(),
                        TimeUnit.HOURS
                );
    }
}
