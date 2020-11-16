package com.github.ethereum.services.impl;

import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.entity.Fee;
import com.github.ethereum.repository.FeeRepo;
import com.github.ethereum.services.IFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Service
@Transactional
@RequiredArgsConstructor
public class FeeService implements IFeeService {

    private final FeeRepo feeRepo;

    @Override
    public Fee create(Fee fee) {
        return this.feeRepo.save(fee);
    }

    @Override
    public Fee readByStatus(EntityStatus status) {
        return this.feeRepo.findByStatus(status);
    }

    @Override
    public void
    update(BigInteger fee, BigInteger gasPrice, EntityStatus status) {
        this.feeRepo.update(fee, gasPrice, status);
    }


}
