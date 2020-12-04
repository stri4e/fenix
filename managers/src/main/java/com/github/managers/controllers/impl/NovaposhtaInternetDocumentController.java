package com.github.managers.controllers.impl;

import com.github.managers.controllers.INovaposhtaInternetDocumentController;
import com.github.managers.dto.NovaposhtaInternetDocumentDto;
import com.github.managers.services.INovaposhtaInternetDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/novaposhta/internet/document")
public class NovaposhtaInternetDocumentController implements INovaposhtaInternetDocumentController {

    private final INovaposhtaInternetDocumentService novaposhtaInternetDocumentService;

    @Override
    public NovaposhtaInternetDocumentDto save(Long orderId, @Valid NovaposhtaInternetDocumentDto payload) {
        return this.novaposhtaInternetDocumentService.save(orderId, payload);
    }

    @Override
    public NovaposhtaInternetDocumentDto findByOrderId(Long orderId) {
        return this.novaposhtaInternetDocumentService.findByOrderId(orderId);
    }
}
