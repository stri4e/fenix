package com.github.admins.controllers;

import com.github.admins.dto.BillDto;
import com.github.admins.payload.EntityStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IBillsController {

    @GetMapping(
            path = "/"
    )
    List<BillDto> findByStatus(@RequestParam(name = "status") EntityStatus status);

}
