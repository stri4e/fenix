package com.github.payments.service;

import com.github.payments.service.impl.BitcoinService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "bitcoin",
        fallback = BitcoinService.class
)
public interface IBitcoinService {

    @DeleteMapping(
            path = "/v1/accounts/{address}"
    )
    void remove(@PathVariable(name = "address") String address);

}
