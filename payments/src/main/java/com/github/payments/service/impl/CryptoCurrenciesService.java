package com.github.payments.service.impl;

import com.github.payments.service.IBitcoinService;
import com.github.payments.service.ICryptoCurrenciesService;
import com.github.payments.service.IEthereumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptoCurrenciesService implements ICryptoCurrenciesService {

    private final IBitcoinService bitcoinService;

    private final IEthereumService ethereumService;

    @Override
    public void remove(String name, String address) {
        switch (name) {
            case "eth":
                this.ethereumService.remove(address);
                break;
            case "btc":
                this.bitcoinService.remove(address);
                break;
            default:
                break;
        }
    }
}
