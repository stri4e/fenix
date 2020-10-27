package com.github.admins.controllers.impl;

import com.github.admins.controllers.IEthereumCurrencyController;
import com.github.admins.dto.CurrencyDto;
import com.github.admins.services.IEthereumCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/ethereum/currency")
public class EthereumCurrencyController implements IEthereumCurrencyController {

    private final IEthereumCurrencyService ethereumCurrencyService;

    @Override
    public void update(CurrencyDto payload) {
        this.ethereumCurrencyService.update(payload);
    }
}
