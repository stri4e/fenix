package com.github.managers.controllers;

import com.github.managers.dto.StaffDto;
import com.github.managers.entity.EntityStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface IStaffController {

    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    StaffDto findStaff(@RequestAttribute UUID managerId);

    @GetMapping(
            path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<StaffDto> findAllStaff();

    @PostMapping(
            path = "/edit/{managerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    StaffDto save(@PathVariable UUID managerId, @Valid @RequestBody StaffDto payload);

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void update(@Valid @RequestBody StaffDto payload);

    @PutMapping(
            path = "/edit/{managerId}/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    void update(
            @PathVariable(name = "managerId") UUID managerId,
            @PathVariable(name = "status") EntityStatus status
    );

}
