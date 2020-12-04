package com.github.managers.services;

import com.github.managers.dto.NovaposhtaInternetDocumentDto;
import com.github.managers.services.impl.NovaposhtaInternetDocumentService;
import com.github.managers.services.impl.NovaposhtaLegalCounterpartyService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(
        name = "deliveries",
        fallback = NovaposhtaInternetDocumentService.class,
        contextId = "internetDocumentId"
)
public interface INovaposhtaInternetDocumentService {

    @PostMapping(
            path = "/edit/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    NovaposhtaInternetDocumentDto save(
            @PathVariable(name = "orderId") Long orderId,
            @Valid @RequestBody NovaposhtaInternetDocumentDto payload
    );

    @GetMapping(
            path = "/fetch/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    NovaposhtaInternetDocumentDto findByOrderId(@PathVariable(name = "orderId") Long orderId);

}
