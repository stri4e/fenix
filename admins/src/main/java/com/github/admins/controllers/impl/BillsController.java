package com.github.admins.controllers.impl;

import com.github.admins.controllers.IBillsController;
import com.github.admins.dto.BillDto;
import com.github.admins.exceptions.BadRequest;
import com.github.admins.payload.EntityStatus;
import com.github.admins.services.IBillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/payments/bills")
public class BillsController implements IBillsController {

    private final IBillsService billsService;

    @Override
    public List<BillDto> findByStatus(EntityStatus status) {
        return this.billsService.findByStatus(status)
                .orElseThrow(BadRequest::new);
    }

}
