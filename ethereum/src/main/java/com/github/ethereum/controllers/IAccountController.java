package com.github.ethereum.controllers;

import com.github.ethereum.dto.AccountDto;
import com.github.ethereum.entity.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;

public interface IAccountController {

    @GetMapping(
            path = "/fetch/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<AccountDto> findByStatus(
            @PathVariable(name = "status") EntityStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "update_at", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

    @GetMapping(
            path = "/address"
    )
    String findAvailableAddress(@RequestAttribute Long userId);

}
