package com.github.deliveries.services.impl;

import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.entity.NovaposhtaSettings;
import com.github.deliveries.exceptions.NotFound;
import com.github.deliveries.repository.NovaposhtaSettingsRepo;
import com.github.deliveries.services.INovaposhtaSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NovaposhtaSettingsService implements INovaposhtaSettingsService {

    private final NovaposhtaSettingsRepo novaposhtaSettingsRepo;

    @Override
    public NovaposhtaSettings create(NovaposhtaSettings settings) {
        return this.novaposhtaSettingsRepo.save(settings);
    }

    @Override
    public NovaposhtaSettings read() {
        return this.novaposhtaSettingsRepo.findByStatus(EntityStatus.on)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(Long id, String baseUrl, String apiKey) {
        this.novaposhtaSettingsRepo.update(id, baseUrl, apiKey);
    }

    @Override
    public void remove(Long id) {
        this.novaposhtaSettingsRepo.delete(id, EntityStatus.off);
    }

}
