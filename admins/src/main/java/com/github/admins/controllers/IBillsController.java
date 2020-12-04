package com.github.admins.controllers;

import com.github.admins.dto.BillDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IBillsController {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<BillDto> findByStatus(@RequestParam(name = "status") String status);

}
