package com.github.ethereum.controllers;

import com.github.ethereum.dto.AccountDto;
import com.github.ethereum.entity.EntityStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.util.List;

public interface IAccountController {

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<AccountDto> findByStatus(
            @PathVariable(name = "status") EntityStatus status
    );

    @GetMapping(
            path = "/fetch/address"
    )
    String findAvailableAddress(@RequestAttribute Long userId);

}
