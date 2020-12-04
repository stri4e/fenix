package com.github.bitcoin.services.impl;

import com.github.bitcoin.entity.Currency;
import com.github.bitcoin.entity.EntityStatus;
import com.github.bitcoin.exceptions.NotFound;
import com.github.bitcoin.repository.CurrencyRepo;
import com.github.bitcoin.services.ICurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyService implements ICurrencyService {

    private final CurrencyRepo currencyRepo;

    @Override
    public void update(Currency currency) {
        this.currencyRepo.save(currency);
    }

    @Override
    public Currency read() {
        return this.currencyRepo.findByStatus(EntityStatus.on)
                .orElseThrow(NotFound::new);
    }
}
