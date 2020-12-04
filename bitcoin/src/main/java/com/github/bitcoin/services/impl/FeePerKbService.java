package com.github.bitcoin.services.impl;

import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.entity.FeePerKb;
import com.github.bitcoin.exceptions.NotFound;
import com.github.bitcoin.repository.FeePerKbRepo;
import com.github.bitcoin.services.IFeePerKbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Service
@Transactional
@RequiredArgsConstructor
public class FeePerKbService implements IFeePerKbService {

    private final FeePerKbRepo feePerKbRepo;

    @Override
    public FeePerKb create(FeePerKb feePerKb) {
        return this.feePerKbRepo.save(feePerKb);
    }

    @Override
    public FeePerKb readActual() {
        return this.feePerKbRepo.findByStatus(EntityStatus.on)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, BigInteger fee) {
        this.feePerKbRepo.update(id, fee);
    }
}
