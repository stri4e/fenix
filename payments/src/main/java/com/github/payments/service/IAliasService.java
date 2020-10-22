package com.github.payments.service;

import com.github.payments.entity.Alias;

public interface IAliasService {

    Alias findByBillId(Long billId);

    Alias create(Alias alias);

    void updateStatus(Long id);

}
