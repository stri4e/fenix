package com.github.payments.service.impl;

import com.github.payments.entity.EntityStatus;
import com.github.payments.entity.Alias;
import com.github.payments.exceptions.NotFound;
import com.github.payments.repository.AliasRepo;
import com.github.payments.service.IAliasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AliasService implements IAliasService {

    private final AliasRepo aliasRepo;

    @Override
    public Alias findByBillId(Long billId) {
        return this.aliasRepo.findByBill_Id(billId)
                .orElseThrow(NotFound::new);
    }

    @Override
    public Alias create(Alias alias) {
        return this.aliasRepo.save(alias);
    }

    @Override
    public void updateStatus(Long id) {
        this.aliasRepo.updateStatus(id, EntityStatus.off);
    }
}
