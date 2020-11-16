package com.github.ethereum.controllers;

import com.github.ethereum.payload.Receipt;
import com.github.ethereum.dto.TransactionDto;
import com.github.ethereum.entity.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ITransactionController {

    @PostMapping(
            path = "/edit/trx",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    TransactionDto sendTransaction(@Valid @RequestBody Receipt payload);

    @PostMapping(
            path = "/edit/contract/{address}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    TransactionDto sendContract(
            @PathVariable(name = "address") String address,
            @Valid @RequestBody Receipt payload
    );

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<TransactionDto> findAllByStatus(
            @PathVariable EntityStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "updateAt", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

}
