package com.github.ethereum.services.impl;

import com.github.ethereum.entity.Currency;
import com.github.ethereum.entity.EntityStatus;
import com.github.ethereum.exceptions.NotFound;
import com.github.ethereum.repository.CurrencyRepo;
import com.github.ethereum.services.ICurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyService implements ICurrencyService {

    private final CurrencyRepo currencyRepo;

    @Override
    public void update(Long id, String name, String fullName, String addressRegex, Integer pow) {
        this.currencyRepo.update(id, name, fullName, addressRegex, pow);
    }

    @Override
    public Currency read() {
        return this.currencyRepo.findByStatus(EntityStatus.on)
                .orElseThrow(NotFound::new);
    }
}
