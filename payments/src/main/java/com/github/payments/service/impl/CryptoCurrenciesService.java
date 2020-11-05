package com.github.payments.service.impl;

import com.github.payments.service.IBitcoinService;
import com.github.payments.service.ICryptoCurrenciesService;
import com.github.payments.service.ICryptoCurrencyMapper;
import com.github.payments.service.IEthereumService;
import com.github.payments.utils.Fallback;
import com.github.payments.utils.Logging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CryptoCurrenciesService implements ICryptoCurrenciesService {

    private final IBitcoinService bitcoinService;

    private final IEthereumService ethereumService;

    private final Fallback fallback;

    private final Map<String, ICryptoCurrencyMapper> mapper = new HashMap<>();

    @Override
    public ICryptoCurrencyMapper chooser(String name) {
        return this.mapper.getOrDefault(name, this.fallback);
    }

    @PostConstruct
    public void init() {
        this.mapper.put("eth", this.ethereumService);
        this.mapper.put("btc", this.bitcoinService);
    }

}
