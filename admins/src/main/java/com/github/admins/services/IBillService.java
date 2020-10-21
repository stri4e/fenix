package com.github.admins.services;

import com.github.admins.dto.BillDto;
import com.github.admins.services.impl.BillService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "payments",
        fallback = BillService.class
)
public interface IBillService {

    @PostMapping(
        path = "/v1/bills/"
    )
    BillDto create(@RequestBody BillDto payload);

    @GetMapping(
            path = "/v1/bills/fetch"
    )
    BillDto findById(@RequestParam(name = "id") Long id);

}