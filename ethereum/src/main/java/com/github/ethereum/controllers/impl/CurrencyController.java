package com.github.ethereum.controllers.impl;

import com.github.ethereum.controllers.ICurrencyController;
import com.github.ethereum.dto.CurrencyDto;
import com.github.ethereum.services.ICurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.ethereum.utils.TransferObj.fromCurrency;
import static com.github.ethereum.utils.TransferObj.toCurrency;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/currency")
public class CurrencyController implements ICurrencyController {

    private final ICurrencyService currencyService;

    @Override
    public void update(CurrencyDto payload) {
        this.currencyService.update(toCurrency(payload));
    }

    @Override
    public CurrencyDto find() {
        return fromCurrency(this.currencyService.read());
    }
}
