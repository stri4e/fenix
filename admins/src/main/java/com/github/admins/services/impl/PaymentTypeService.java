package com.github.admins.services.impl;

import com.github.admins.dto.PaymentTypesDto;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.IPaymentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentTypeService implements IPaymentTypeService {
    @Override
    public Optional<List<PaymentTypesDto>> findAllByStatus(EntityStatus status) {
        return Optional.empty();
    }

    @Override
    public void update(PaymentTypesDto payload) {

    }

    @Override
    public void remove(Long id) {

    }
}
