package com.github.websocket.controllers;

import com.github.websocket.network.Broker;
import com.github.websocket.dto.BillDto;
import com.github.websocket.utils.Logging;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/bills")
@RequiredArgsConstructor
public class BillsController {

    private final Broker broker;

    @PostMapping(
            path = "/{ending}"
    )
    @HystrixCommand
    @ResponseStatus(HttpStatus.CREATED)
    @Logging(isTime = true, isReturn = false)
    void billNotify(@PathVariable(name = "ending") String ending, @RequestBody BillDto payload) {
        this.broker.sendBill(ending, payload);
    }

}
