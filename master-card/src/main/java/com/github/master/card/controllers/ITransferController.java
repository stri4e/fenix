package com.github.master.card.controllers;

import com.github.master.card.payload.Receipt;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

public interface ITransferController {

    @PostMapping(
            path = "/{billId}"
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    void sendTransfer(
            @PathVariable(value = "billId") Long billId,
            @Valid @RequestBody Receipt payload
    );

}
