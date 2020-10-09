package com.github.payments.controllers.impl;

import com.github.payments.controllers.IPaymentTypeController;
import com.github.payments.service.IPaymentTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/payment/type")
public class PaymentTypeController implements IPaymentTypeController {

    private final IPaymentTypesService paymentTypesService;

}
