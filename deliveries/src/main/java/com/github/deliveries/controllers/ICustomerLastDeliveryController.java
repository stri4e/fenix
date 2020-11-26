package com.github.deliveries.controllers;

import com.github.deliveries.dto.CustomerLastDeliveryDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.UUID;

public interface ICustomerLastDeliveryController {

    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CustomerLastDeliveryDto findByUserId(@RequestAttribute(name = "userId") UUID userId);

    @GetMapping(
            path = "/fetch/{deliveryId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CustomerLastDeliveryDto findByUserId(@PathVariable Long deliveryId);

    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    CustomerLastDeliveryDto save(@ApiIgnore @RequestAttribute(name = "userId") UUID userId,
                                 @Valid @RequestBody CustomerLastDeliveryDto payload);

    @PutMapping
    void updateDelivery(@Valid @RequestBody CustomerLastDeliveryDto payload);

}
