package com.github.payments.service.impl;

import com.github.payments.entity.Who;
import com.github.payments.repository.WhoRepo;
import com.github.payments.service.IWhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WhoService implements IWhoService {

    private final WhoRepo whoRepo;

    @Override
    public Who create(Who who) {
        return this.whoRepo.save(who);
    }
}
