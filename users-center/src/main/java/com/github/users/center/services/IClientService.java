package com.github.users.center.services;

import com.github.users.center.dto.ClientDto;
import com.github.users.center.services.impl.ClientService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "statistics",
        fallback = ClientService.class,
        contextId = "clientsId"
)
public interface IClientService {

    @PostMapping(
            path = "/v1/clients/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    void create(@RequestBody ClientDto payload);

}
