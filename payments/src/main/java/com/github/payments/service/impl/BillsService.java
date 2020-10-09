package com.github.payments.service.impl;

import com.github.payments.entity.Bill;
import com.github.payments.entity.EntityStatus;
import com.github.payments.exceptions.NotFound;
import com.github.payments.repository.BillRepo;
import com.github.payments.service.IBillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BillsService implements IBillsService {

    private final BillRepo billRepo;

    @Override
    public Bill create(Bill bill) {
        return this.billRepo.save(bill);
    }

    @Override
    public List<Bill> readByStatus(EntityStatus status) {
        return this.billRepo.findAllByStatus(status);
    }

    @Override
    public Bill readById(Long id) {
        return this.billRepo.findById(id)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Bill readByByAddressAndStatus(String address, EntityStatus status) {
        return this.billRepo.findByAddressAndStatus(address, status)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Bill bill) {
        this.billRepo.save(bill);
    }

    @Override
    public void remove(Long id) {
        this.billRepo.update(id, EntityStatus.off);
    }
}
