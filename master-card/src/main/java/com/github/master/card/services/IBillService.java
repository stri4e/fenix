package com.github.master.card.services;

import com.github.master.card.services.impl.BillService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.math.BigInteger;

@FeignClient(
        name = "payments",
        fallback = BillService.class
)
public interface IBillService {

    @PutMapping(
            path = "/v1/bills/edit/mastercard/{billId}/{value}/{transfer}"
    )
    void update(
            @PathVariable(name = "billId") Long billId,
            @PathVariable(name = "value") BigInteger value,
            @PathVariable(name = "transfer") String transfer
    );

}
