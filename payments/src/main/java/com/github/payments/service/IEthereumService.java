package com.github.payments.service;

import com.github.payments.service.impl.EthereumService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ethereum",
        fallback = EthereumService.class
)
public interface IEthereumService extends ICryptoCurrencyMapper {

    @Override
    @DeleteMapping(
            path = "/v1/accounts/{address}"
    )
    void remove(@PathVariable(name = "address") String address);

}
