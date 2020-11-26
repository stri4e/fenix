package com.github.deliveries.services.impl;

import com.github.deliveries.entity.EntityStatus;
import com.github.deliveries.entity.NovaposhtaLegalCounterparty;
import com.github.deliveries.exceptions.NotFound;
import com.github.deliveries.repository.NovaposhtaLegalCounterpartyRepo;
import com.github.deliveries.services.INovaposhtaLegalCounterpartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NovaposhtaLegalCounterpartyService implements INovaposhtaLegalCounterpartyService {

    private final NovaposhtaLegalCounterpartyRepo novaposhtaLegalCounterpartyRepo;

    @Override
    public NovaposhtaLegalCounterparty create(NovaposhtaLegalCounterparty nlc) {
        return this.novaposhtaLegalCounterpartyRepo.save(nlc);
    }

    @Override
    public NovaposhtaLegalCounterparty read() {
        return this.novaposhtaLegalCounterpartyRepo.findByStatus(EntityStatus.on)
                .orElseThrow(NotFound::new);
    }

    @Override
    public void update(NovaposhtaLegalCounterparty nkc) {
        this.novaposhtaLegalCounterpartyRepo.save(nkc);
    }

    @Override
    public void remove(Long id) {
        this.novaposhtaLegalCounterpartyRepo.remove(id, EntityStatus.off);
    }
}
