package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.ICurrencyController;
import com.github.bitcoin.dto.CurrencyDto;
import com.github.bitcoin.services.ICurrencyService;
import com.github.bitcoin.utils.Logging;
import com.github.bitcoin.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void updateCurrency(CurrencyDto payload) {
        this.currencyService.update(toCurrency(payload));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public CurrencyDto findCurrency() {
        return fromCurrency(this.currencyService.read());
    }

}
