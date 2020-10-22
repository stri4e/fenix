package com.github.websocket.controllers;

import com.github.websocket.network.Broker;
import com.github.websocket.payload.BillDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/bills")
@RequiredArgsConstructor
public class BillsController {

    private final Broker broker;

    @PostMapping(
            path = "/{ending}"
    )
    void billNotify(@PathVariable String ending, @RequestBody BillDto payload) {
        this.broker.sendBill(ending, payload);
    }

}
