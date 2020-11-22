package com.github.payments.controllers;

import com.github.payments.dto.BillDto;
import com.github.payments.entity.EntityStatus;
import com.github.payments.payload.Report;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.UUID;

public interface IBillsController {

    @PostMapping(
            path = "/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    BillDto save(@PathVariable Long orderId, @RequestBody BillDto payload);

    @GetMapping(
            path = "/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    Object findByParams(
            @RequestParam(required = false, name = "id") Long id,
            @RequestParam(required = false, name = "status") EntityStatus status
    );

    @PutMapping(
            path = "/edit/crypto/{address}/{value}/{transfer}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    Report updateCrypto(
            @PathVariable(name = "address") String address,
            @PathVariable(name = "value") BigInteger value,
            @PathVariable(name = "transfer") String transfer
    );

    @PutMapping(
            path = "/edit/mastercard/{billId}/{value}/{transfer}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void updateMastercard(
            @PathVariable(name = "billId") Long billId,
            @PathVariable(name = "value") BigInteger value,
            @PathVariable(name = "transfer") String transfer
    );

    @DeleteMapping(
            path = "/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "id") Long id);

}
