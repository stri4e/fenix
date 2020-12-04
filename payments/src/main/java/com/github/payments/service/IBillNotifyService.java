package com.github.payments.service;

import com.github.payments.dto.BillDto;
import com.github.payments.service.impl.BillNotifyService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "websocket",
        fallback = BillNotifyService.class
)
public interface IBillNotifyService {

    @PostMapping(
            path = "/v1/bills/edit/{ending}"
    )
    void billNotify(@PathVariable String ending, @RequestBody BillDto payload);

}
