package com.github.payments.service;

import com.github.payments.entity.Bill;
import com.github.payments.entity.EntityStatus;

import java.util.List;

public interface IBillsService {

    Bill create(Bill bill);

    List<Bill> readByStatus(EntityStatus status);

    Bill readById(Long id);

    Bill readByByAddressAndStatus(String address, EntityStatus status);

    void update(Bill bill);

    void remove(Long id);

}
