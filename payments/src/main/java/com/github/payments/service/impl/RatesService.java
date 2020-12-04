package com.github.payments.service.impl;

import com.github.payments.entity.Rate;
import com.github.payments.repository.RateRepo;
import com.github.payments.service.IRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RatesService implements IRatesService {

    private final RateRepo rateRepo;

    @Override
    public List<Rate> saveAll(List<Rate> rates) {
        return this.rateRepo.saveAll(rates);
    }

}
