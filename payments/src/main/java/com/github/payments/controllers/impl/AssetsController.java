package com.github.payments.controllers.impl;

import com.github.payments.controllers.IAssetsController;
import com.github.payments.service.IAssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/crypto/currencies")
public class AssetsController implements IAssetsController {

    private final IAssetsService currencyService;



}
