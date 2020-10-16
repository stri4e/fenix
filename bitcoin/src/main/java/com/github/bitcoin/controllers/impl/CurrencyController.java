package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.ICurrencyController;
import com.github.bitcoin.dto.CurrencyDto;
import com.github.bitcoin.services.ICurrencyService;
import com.github.bitcoin.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.bitcoin.utils.TransferObj.fromCurrency;
import static com.github.bitcoin.utils.TransferObj.toCurrency;

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
