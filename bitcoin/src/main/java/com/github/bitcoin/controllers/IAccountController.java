package com.github.bitcoin.controllers;

import com.github.bitcoin.dto.AccountDto;
import com.github.bitcoin.entity.EntityStatus;
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
    Page<AccountDto> findByStatus(
            @PathVariable(name = "status") EntityStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "updateAt", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    void save(@RequestAttribute UUID userId);

    @GetMapping(
            path = "/address"
    )
    @ResponseStatus(code = HttpStatus.OK)
    String findAvailableAddress(@RequestAttribute UUID userId);

    @DeleteMapping(
            path = "/{address}"
    )
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void remove(@PathVariable(name = "address") String address);

}
