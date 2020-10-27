package com.github.admins.services;

import com.github.admins.dto.EthereumTransactionDto;
import com.github.admins.payload.EntityStatus;
import com.github.admins.payload.Receipt;
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

import java.util.Optional;

public interface IEthereumTransactionService {

    @PostMapping(
            path = "/v1/edit/trx",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<EthereumTransactionDto> sendTransaction(@RequestBody Receipt payload);

    @PostMapping(
            path = "/v1/edit/contract/{address}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<EthereumTransactionDto> sendContract(
            @PathVariable(name = "address") String address,
            @RequestBody Receipt payload
    );

    @GetMapping(
            path = "/v1/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<EthereumTransactionDto> findAllByStatus(
            @PathVariable EntityStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "update_at", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

}
