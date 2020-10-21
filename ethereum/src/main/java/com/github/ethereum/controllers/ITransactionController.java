package com.github.ethereum.controllers;

import com.github.ethereum.payload.Receipt;
import com.github.ethereum.dto.TransactionDto;
import com.github.ethereum.entity.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ITransactionController {

    @PostMapping(
            path = "/edit/trx",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    TransactionDto sendTransaction(@RequestBody Receipt payload);

    @PostMapping(
            path = "/edit/contract/{address}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    TransactionDto sendContract(
            @PathVariable(name = "address") String address,
            @RequestBody Receipt payload
    );

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<TransactionDto> findAllByStatus(
            @PathVariable EntityStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "update_at", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

}