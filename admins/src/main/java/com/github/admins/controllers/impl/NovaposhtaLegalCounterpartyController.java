package com.github.admins.controllers.impl;

import com.github.admins.controllers.INovaposhtaLegalCounterpartyController;
import com.github.admins.dto.NovaposhtaLegalCounterpartyDto;
import com.github.admins.services.INovaposhtaLegalCounterpartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/novaposhta/counterparty")
public class NovaposhtaLegalCounterpartyController implements INovaposhtaLegalCounterpartyController {

    private final INovaposhtaLegalCounterpartyService novaposhtaLegalCounterpartyService;

    @Override
    public NovaposhtaLegalCounterpartyDto save(@Valid NovaposhtaLegalCounterpartyDto payload) {
        return this.novaposhtaLegalCounterpartyService.create(payload);
    }

    @Override
    public NovaposhtaLegalCounterpartyDto find() {
        return this.novaposhtaLegalCounterpartyService.read();
    }

    @Override
    public void update(@Valid NovaposhtaLegalCounterpartyDto payload) {
        this.novaposhtaLegalCounterpartyService.update(payload);
    }

    @Override
    public void remove(Long id) {
        this.novaposhtaLegalCounterpartyService.remove(id);
    }

}
