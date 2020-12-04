package com.github.deliveries.services.impl;

import com.github.deliveries.entity.NovaposhtaInternetDocument;
import com.github.deliveries.exceptions.NotFound;
import com.github.deliveries.repository.NovaposhtaInternetDocumentRepo;
import com.github.deliveries.services.INovaposhtaInternetDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NovaposhtaInternetDocumentService implements INovaposhtaInternetDocumentService {

    private final NovaposhtaInternetDocumentRepo novaposhtaInternetDocumentRepo;

    @Override
    public NovaposhtaInternetDocument create(NovaposhtaInternetDocument nid) {
        return this.novaposhtaInternetDocumentRepo.save(nid);
    }

    @Override
    public NovaposhtaInternetDocument readByOrderId(Long orderId) {
        return this.novaposhtaInternetDocumentRepo.findByOrderId(orderId)
                .orElseThrow(NotFound::new);
    }
}
