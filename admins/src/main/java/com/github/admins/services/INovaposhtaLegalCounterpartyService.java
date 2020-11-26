package com.github.admins.services;

import com.github.admins.dto.NovaposhtaLegalCounterpartyDto;
import com.github.admins.services.impl.NovaposhtaLegalCounterpartyService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(
        name = "deliveries",
        fallback = NovaposhtaLegalCounterpartyService.class,
        contextId = "deliveriId"
)
public interface INovaposhtaLegalCounterpartyService {

    @PostMapping(
            path = "/v1/novaposhta/counterparty/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    NovaposhtaLegalCounterpartyDto create(@Valid @RequestBody NovaposhtaLegalCounterpartyDto payload);

    @GetMapping(
            path = "/v1/novaposhta/counterparty/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    NovaposhtaLegalCounterpartyDto read();

    @PutMapping(
            path = "/v1/novaposhta/counterparty/edit"
    )
    @ResponseStatus(value = HttpStatus.OK)
    void update(@Valid @RequestBody NovaposhtaLegalCounterpartyDto payload);

    @DeleteMapping(
            path = "/v1/novaposhta/counterparty/edit/{id}"
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
