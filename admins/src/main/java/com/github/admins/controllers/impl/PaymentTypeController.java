package com.github.admins.controllers.impl;

import com.github.admins.controllers.IPaymentTypeController;
import com.github.admins.dto.PaymentTypesDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.IPaymentTypeService;
import com.github.admins.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/payments/types")
public class PaymentTypeController implements IPaymentTypeController {

    private final IPaymentTypeService paymentTypeService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<PaymentTypesDto> findAllByStatus(String status) {
        return this.paymentTypeService.findAllByStatus(status)
                .orElseThrow(NotFound::new);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(PaymentTypesDto payload) {
        this.paymentTypeService.update(payload);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.paymentTypeService.remove(id);
    }
}
