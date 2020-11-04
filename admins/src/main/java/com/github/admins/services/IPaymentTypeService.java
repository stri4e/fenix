package com.github.admins.services;

import com.github.admins.dto.PaymentTypesDto;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.impl.BillsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "payments",
        fallback = BillsService.class,
        contextId = "paymentTypeId"
)
public interface IPaymentTypeService {

    @GetMapping(
            path = "/v1/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<List<PaymentTypesDto>> findAllByStatus(
            @PathVariable(value = "status") EntityStatus status
    );

    @PutMapping(
            path = "/v1/edit"
    )
    void update(@RequestBody PaymentTypesDto payload);

    @DeleteMapping(
            path = "/v1/edit/{id}"
    )
    void remove(@PathVariable(value = "id") Long id);

}
