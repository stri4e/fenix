package com.github.admins.services;

import com.github.admins.dto.ManagerDto;
import com.github.admins.services.impl.ManagersService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "managers",
        fallback = ManagersService.class,
        contextId = "managersId"
)
public interface IManagersService {

    @GetMapping(
            path = "/v1/fetch/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<ManagerDto> findManager(@PathVariable(name = "orderId") Long orderId);


}
