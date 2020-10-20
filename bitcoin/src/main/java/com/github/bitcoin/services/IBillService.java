package com.github.bitcoin.services;

import com.github.bitcoin.payload.Report;
import com.github.bitcoin.services.impl.BillService;
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
        path = "/v1/bills/edit/{address}/{value}/{transfer}"
    )
    Report update(
            @PathVariable(name = "address") String address,
            @PathVariable(name = "value") BigInteger value,
            @PathVariable(name = "transfer") String transfer
    );

}
