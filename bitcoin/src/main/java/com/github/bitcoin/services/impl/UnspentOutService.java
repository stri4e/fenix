package com.github.bitcoin.services.impl;

import com.github.bitcoin.entity.UnspentOut;
import com.github.bitcoin.repository.UnspentOutRepo;
import com.github.bitcoin.services.IUnspentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UnspentOutService implements IUnspentOutService {

    private final UnspentOutRepo unspentOutRepo;

    @Override
    public UnspentOut create(UnspentOut unspentOut) {
        return this.unspentOutRepo.save(unspentOut);
    }
}
