package com.github.orders.service;

import com.github.orders.dto.BillDto;
import com.github.orders.service.impl.BillService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "payments",
        fallback = BillService.class
)
public interface IBillService {

    @PostMapping(
        path = "/v1/bills/def"
    )
    BillDto create(@RequestBody BillDto payload);

    @GetMapping(
            path = "/v1/bills/fetch"
    )
    BillDto findById(@RequestParam(name = "id") Long id);

    @DeleteMapping(
            path = "/{id}"
    )
    void remove(@PathVariable(name = "id") Long id);

}
