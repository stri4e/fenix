package com.github.bitcoin.controllers.impl;

import com.github.bitcoin.controllers.ICurrencyController;
import com.github.bitcoin.services.ICurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/currency")
public class CurrencyController implements ICurrencyController {

    private final ICurrencyService currencyService;

}
