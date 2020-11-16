package com.github.payments.service.impl;

import com.github.payments.entity.CurrentRate;
import com.github.payments.repository.CurrentRateRepo;
import com.github.payments.service.ICurrentRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrentRateService implements ICurrentRateService {

    private final CurrentRateRepo currentRateRepo;

    @Override
    public void saveAll(List<CurrentRate> rates) {
        this.currentRateRepo.saveAll(rates);
    }

    @Override
    public List<CurrentRate> readAll() {
        LocalDateTime now = LocalDateTime.now();
        return this.currentRateRepo
                .findAllByCreateAtLessThan(now);
    }
}
