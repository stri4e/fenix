package com.github.managers.controllers.impl;

import com.github.managers.controllers.INovaposhtaLegalCounterpartyController;
import com.github.managers.dto.NovaposhtaLegalCounterpartyDto;
import com.github.managers.services.INovaposhtaLegalCounterpartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/novaposhta/counterparty")
public class NovaposhtaLegalCounterpartyController implements INovaposhtaLegalCounterpartyController {

    private final INovaposhtaLegalCounterpartyService novaposhtaLegalCounterpartyService;

    @Override
    public NovaposhtaLegalCounterpartyDto find() {
        return this.novaposhtaLegalCounterpartyService.read();
    }

}
