package com.github.payments.controllers;

import com.github.payments.dto.PaymentTypesDto;
import com.github.payments.entity.EntityStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IPaymentTypeController {

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    PaymentTypesDto save(@Valid @RequestBody PaymentTypesDto payload);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<PaymentTypesDto> findAll();

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(code = HttpStatus.OK)
    List<PaymentTypesDto> findAllByStatus(
            @PathVariable(value = "status") EntityStatus status
    );

    @PutMapping(
            path = "/edit"
    )
    @ResponseStatus(code = HttpStatus.OK)
    void update(@Valid @RequestBody PaymentTypesDto payload);

    @DeleteMapping(
            path = "/edit/{id}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(value = "id") Long id);

}
