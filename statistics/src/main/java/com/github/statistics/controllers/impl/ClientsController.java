package com.github.statistics.controllers.impl;

import com.github.statistics.controllers.IClientsController;
import com.github.statistics.dto.ClientDto;
import com.github.statistics.services.IClientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.statistics.utils.TransferObj.toClient;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/clients")
public class ClientsController implements IClientsController {

    private final IClientsService clientService;

    @Override
    public void save(@Valid ClientDto payload) {
        this.clientService.create(toClient(payload));
    }

    @Override
    public Long countAllClient() {
        return this.clientService.countAll();
    }

}
