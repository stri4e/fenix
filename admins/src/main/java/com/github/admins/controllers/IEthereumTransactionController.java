package com.github.admins.controllers;

import com.github.admins.dto.EthereumTransactionDto;
import com.github.admins.payload.EntityStatus;
import com.github.admins.payload.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

public interface IEthereumTransactionController {

    @PostMapping(
            path = "/trx",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    EthereumTransactionDto sendTransaction(@RequestBody Receipt payload);

    @PostMapping(
            path = "/contract/{address}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    EthereumTransactionDto sendContract(
            @PathVariable(name = "address") String address,
            @RequestBody Receipt payload
    );

    @GetMapping(
            path = "/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Page<EthereumTransactionDto> findAllByStatus(
            @PathVariable EntityStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "update_at", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

}
