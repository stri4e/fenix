package com.github.payments.service;

import com.github.payments.entity.EntityStatus;
import com.github.payments.entity.PaymentTypes;

import java.util.List;

public interface IPaymentTypesService {

    PaymentTypes create(PaymentTypes paymentTypes);

    List<PaymentTypes> readAll();

    List<PaymentTypes> readAllByStatus(EntityStatus status);

    PaymentTypes readByAlias(String alias);

    void update(PaymentTypes paymentTypes);

    void remove(Long id);

}
