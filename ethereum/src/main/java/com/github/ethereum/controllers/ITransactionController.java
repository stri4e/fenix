package com.github.ethereum.controllers;

import com.github.ethereum.dto.ReceiptDto;
import com.github.ethereum.dto.TransactionDto;
import com.github.ethereum.entity.EntityStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ITransactionController {

    @PostMapping(
            path = "/edit/trx",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    TransactionDto sendTransaction(@RequestBody ReceiptDto payload);

    @PostMapping(
            path = "/edit/contract/{address}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    TransactionDto sendContract(
            @PathVariable(name = "address") String address,
            @RequestBody ReceiptDto payload
    );

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<TransactionDto> findAllByStatus(@PathVariable EntityStatus status);

}
