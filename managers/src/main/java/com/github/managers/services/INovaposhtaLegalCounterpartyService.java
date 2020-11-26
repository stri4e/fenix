package com.github.managers.services;

import com.github.managers.dto.NovaposhtaLegalCounterpartyDto;
import com.github.managers.services.impl.NovaposhtaLegalCounterpartyService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "deliveries",
        fallback = NovaposhtaLegalCounterpartyService.class,
        contextId = "legalCounterpartyId"
)
public interface INovaposhtaLegalCounterpartyService {

    @GetMapping(
            path = "/v1/novaposhta/counterparty/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    NovaposhtaLegalCounterpartyDto read();

}
