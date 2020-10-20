package com.github.payments.controllers;

import com.github.payments.dto.BillDto;
import com.github.payments.entity.EntityStatus;
import com.github.payments.payload.Report;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

public interface IBillsController {

    @PostMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    BillDto save(@RequestBody BillDto payload);

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
            path = "/edit/{address}/{value}/{transfer}"
    )
    @ResponseStatus(code = HttpStatus.OK)
    Report update(
            @PathVariable(name = "address") String address,
            @PathVariable(name = "value") BigInteger value,
            @PathVariable(name = "transfer") String transfer
    );

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "id") Long id);

}
