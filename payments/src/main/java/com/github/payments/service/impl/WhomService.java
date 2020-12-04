package com.github.payments.service.impl;

import com.github.payments.entity.Whom;
import com.github.payments.repository.WhomRepo;
import com.github.payments.service.IWhomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WhomService implements IWhomService {

    private final WhomRepo whomRepo;

    @Override
    public Whom create(Whom whom) {
        return this.whomRepo.save(whom);
    }
}
