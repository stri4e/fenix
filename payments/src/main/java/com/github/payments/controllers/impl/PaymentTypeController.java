package com.github.payments.controllers.impl;

import com.github.payments.controllers.IPaymentTypeController;
import com.github.payments.dto.PaymentTypesDto;
import com.github.payments.entity.EntityStatus;
import com.github.payments.entity.PaymentTypes;
import com.github.payments.service.IPaymentTypesService;
import com.github.payments.utils.Logging;
import com.github.payments.utils.TransferObj;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.payments.utils.TransferObj.fromPaymentType;
import static com.github.payments.utils.TransferObj.toPaymentType;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/payment/type")
public class PaymentTypeController implements IPaymentTypeController {

    private final IPaymentTypesService paymentTypesService;

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public PaymentTypesDto save(PaymentTypesDto payload) {
        return fromPaymentType(this.paymentTypesService.create(toPaymentType(payload)));
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<PaymentTypesDto> findAll() {
        return this.paymentTypesService.readAll().stream()
                .map(TransferObj::fromPaymentType)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public List<PaymentTypesDto> findAllByStatus(EntityStatus status) {
        return this.paymentTypesService.readAllByStatus(status).stream()
                .map(TransferObj::fromPaymentType)
                .collect(Collectors.toList());
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void update(PaymentTypesDto payload) {
        PaymentTypes payment = this.paymentTypesService.readByAlias(payload.getAlias());
        payment.setAlias(payload.getAlias());
        this.paymentTypesService.update(payment);
    }

    @Override
    @HystrixCommand
    @Logging(isTime = true, isReturn = false)
    public void remove(Long id) {
        this.paymentTypesService.remove(id);
    }
}
