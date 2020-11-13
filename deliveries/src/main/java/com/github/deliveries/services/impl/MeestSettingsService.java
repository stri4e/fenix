package com.github.deliveries.services.impl;

import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.entity.MeestSettings;
import com.github.deliveries.exceptions.NotFound;
import com.github.deliveries.repository.MeestSettingsRepo;
import com.github.deliveries.services.IMeestSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MeestSettingsService implements IMeestSettingsService {

    private final MeestSettingsRepo meestSettingsRepo;

    @Override
    public MeestSettings create(MeestSettings meestSettings) {
        return this.meestSettingsRepo.save(meestSettings);
    }

    @Override
    public MeestSettings read() {
        return this.meestSettingsRepo.findByStatus(EntityStatus.on)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(MeestSettings settings) {
        this.meestSettingsRepo.save(settings);
    }

    @Override
    public void remove(Long id) {
        this.meestSettingsRepo.delete(id, EntityStatus.off);
    }
}
