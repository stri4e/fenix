package com.github.managers.services.impl;

import com.github.managers.dto.NovaposhtaInternetDocumentDto;
import com.github.managers.services.INovaposhtaInternetDocumentService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class NovaposhtaInternetDocumentService implements INovaposhtaInternetDocumentService {

    @Override
    public NovaposhtaInternetDocumentDto save(Long orderId, @Valid NovaposhtaInternetDocumentDto payload) {
        return null;
    }

    @Override
    public NovaposhtaInternetDocumentDto findByOrderId(Long orderId) {
        return null;
    }
}
