package com.github.deliveries.services.impl;

import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.entity.MeestSession;
import com.github.deliveries.repository.MeestSessionRepo;
import com.github.deliveries.services.IMeestSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class MeestSessionService implements IMeestSessionService {

    private final MeestSessionRepo meestSessionRepo;

    @Override
    public MeestSession create(MeestSession tokens) {
        return this.meestSessionRepo.save(tokens);
    }

    @Override
    public MeestSession read() {
        return this.meestSessionRepo.findByStatus(EntityStatus.on)
                .orElse(null);
    }

    @Override
    public void update(Long id, String token, String refreshToken, Date expireIn) {
        this.meestSessionRepo.update(id, token, refreshToken, expireIn);
    }

    @Override
    public void remove(Long id) {
        this.meestSessionRepo.delete(id, EntityStatus.off);
    }
}
