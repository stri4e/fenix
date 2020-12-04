package com.github.admins.services;

import com.github.admins.dto.BillDto;
import com.github.admins.services.impl.BillsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "payments",
        fallback = BillsService.class,
        contextId = "billId"
)
public interface IBillsService {

    @GetMapping(
            path = "/v1/bills/fetch"
    )
    Optional<List<BillDto>> findByStatus(@RequestParam(name = "status") String status);

}
