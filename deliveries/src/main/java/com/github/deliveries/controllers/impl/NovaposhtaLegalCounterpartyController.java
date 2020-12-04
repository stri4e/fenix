package com.github.deliveries.controllers.impl;

import com.github.deliveries.controllers.INovaposhtaLegalCounterpartyController;
import com.github.deliveries.dto.NovaposhtaLegalCounterpartyDto;
import com.github.deliveries.entity.NovaposhtaLegalCounterparty;
import com.github.deliveries.services.INovaposhtaLegalCounterpartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.deliveries.utils.TransferObj.fromCounterparty;
import static com.github.deliveries.utils.TransferObj.toCounterparty;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/novaposhta/counterparty")
public class NovaposhtaLegalCounterpartyController implements INovaposhtaLegalCounterpartyController {

    private final INovaposhtaLegalCounterpartyService novaposhtaLegalCounterpartyService;

    @Override
    public NovaposhtaLegalCounterpartyDto save(@Valid NovaposhtaLegalCounterpartyDto payload) {
        return fromCounterparty(this.novaposhtaLegalCounterpartyService.create(toCounterparty(payload)));
    }

    @Override
    public NovaposhtaLegalCounterpartyDto find() {
        return fromCounterparty(this.novaposhtaLegalCounterpartyService.read());
    }

    @Override
    public void update(@Valid NovaposhtaLegalCounterpartyDto payload) {
        NovaposhtaLegalCounterparty nlc = this.novaposhtaLegalCounterpartyService.read();
        nlc.setRef(payload.getRef());
        nlc.setDescription(payload.getDescription());
        nlc.setFirstName(payload.getFirstName());
        nlc.setMiddleName(payload.getMiddleName());
        nlc.setLastName(payload.getLastName());
        nlc.setCounterparty(payload.getCounterparty());
        nlc.setOwnershipForm(payload.getOwnershipForm());
        nlc.setEdrpou(payload.getEdrpou());
        nlc.setCounterpartyType(payload.getCounterpartyType());
        this.novaposhtaLegalCounterpartyService.update(nlc);
    }

    @Override
    public void remove(Long id) {
        this.novaposhtaLegalCounterpartyService.remove(id);
    }

}
