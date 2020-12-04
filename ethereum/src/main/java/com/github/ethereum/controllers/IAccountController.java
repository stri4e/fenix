package com.github.ethereum.controllers;

import com.github.ethereum.dto.AccountDto;
import com.github.ethereum.entity.EntityStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface IAccountController {

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    Page<AccountDto> findByStatus(
            @PathVariable(name = "status") EntityStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "updateAt", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    AccountDto save(@RequestAttribute(name = "userId") UUID userId);

    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    @GetMapping(
            path = "/address"
    )
    @ResponseStatus(value = HttpStatus.OK)
    String findAvailableAddress(@RequestAttribute(name = "userId") UUID userId);

    @PutMapping(
            path = "/{address}"
    )
    @ResponseStatus(value = HttpStatus.OK)
    void activateAddress(@PathVariable(name = "address") String address);

    @DeleteMapping(
            path = "/{address}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "address") String address);

}
