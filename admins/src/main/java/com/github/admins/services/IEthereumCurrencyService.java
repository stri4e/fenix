package com.github.admins.services;

import com.github.admins.dto.CurrencyDto;
import com.github.admins.services.impl.EthereumCurrencyService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ethereum",
        fallback = EthereumCurrencyService.class,
        contextId = "ethereumCurrencyId"
)
public interface IEthereumCurrencyService {

    @PutMapping(
            path = "/v1/edit"
    )
    void update(@RequestBody CurrencyDto payload);

}
