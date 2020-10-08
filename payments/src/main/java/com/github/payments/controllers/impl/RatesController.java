package com.github.payments.controllers.impl;

import com.github.payments.controllers.IRatesController;
import com.github.payments.service.IRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/rates")
public class RatesController implements IRatesController {

    private final IRatesService rateService;

}
