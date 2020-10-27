package com.github.admins.controllers;

import com.github.admins.dto.EthereumAccountDto;
import com.github.admins.payload.EntityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IEthereumAccountController {

    @GetMapping(
            path = "/{status}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Page<EthereumAccountDto> findByStatus(
            @PathVariable(name = "status") EntityStatus status,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults(value = {
                    @SortDefault(sort = "update_at", direction = Sort.Direction.ASC),
            }) Pageable pageable
    );

}
