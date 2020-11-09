package com.github.payments.service.impl;

import com.github.payments.entity.EntityStatus;
import com.github.payments.entity.PaymentTypes;
import com.github.payments.exceptions.NotFound;
import com.github.payments.repository.PaymentTypesRepo;
import com.github.payments.service.IPaymentTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentTypesService implements IPaymentTypesService {

    private final PaymentTypesRepo paymentTypesRepo;

    @Override
    public PaymentTypes create(PaymentTypes paymentTypes) {
        return this.paymentTypesRepo.save(paymentTypes);
    }

    @Override
    public List<PaymentTypes> readAll() {
        return this.paymentTypesRepo.findAllByStatus(EntityStatus.on);
    }

    @Override
    public List<PaymentTypes> readAllByStatus(EntityStatus status) {
        return this.paymentTypesRepo.findAllByStatus(status);
    }

    @Override
    public PaymentTypes readByAlias(String alias) {
        return this.paymentTypesRepo.findByAlias(alias)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(PaymentTypes paymentTypes) {
        this.paymentTypesRepo.save(paymentTypes);
    }

    @Override
    public void updateAlias(Long id, String alias) {
        this.paymentTypesRepo.updateAlias(id, alias);
    }

    @Override
    public void remove(Long id) {
        this.paymentTypesRepo.update(id, EntityStatus.off);
    }
}
