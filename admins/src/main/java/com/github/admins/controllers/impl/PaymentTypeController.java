package com.github.admins.controllers.impl;

import com.github.admins.controllers.IPaymentTypeController;
import com.github.admins.dto.PaymentTypesDto;
import com.github.admins.exceptions.NotFound;
import com.github.admins.services.IPaymentTypeService;
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
    public List<PaymentTypesDto> findAllByStatus(String status) {
        return this.paymentTypeService.findAllByStatus(status)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(PaymentTypesDto payload) {
        this.paymentTypeService.update(payload);
    }

    @Override
    public void remove(Long id) {
        this.paymentTypeService.remove(id);
    }
}
