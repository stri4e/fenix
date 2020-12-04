package com.github.deliveries.controllers.impl;

import com.github.deliveries.controllers.INovaposhtaInternetDocumentController;
import com.github.deliveries.dto.NovaposhtaInternetDocumentDto;
import com.github.deliveries.services.INovaposhtaInternetDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.deliveries.utils.TransferObj.fromNID;
import static com.github.deliveries.utils.TransferObj.toNID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/novaposhta/internet/document")
public class NovaposhtaInternetDocumentController implements INovaposhtaInternetDocumentController {

    private final INovaposhtaInternetDocumentService novaposhtaInternetDocumentService;

    @Override
    public NovaposhtaInternetDocumentDto save(Long orderId, @Valid NovaposhtaInternetDocumentDto payload) {
        return fromNID(this.novaposhtaInternetDocumentService.create(toNID(payload, orderId)));
    }

    @Override
    public NovaposhtaInternetDocumentDto findByOrderId(Long orderId) {
        return fromNID(this.novaposhtaInternetDocumentService.readByOrderId(orderId));
    }

}
