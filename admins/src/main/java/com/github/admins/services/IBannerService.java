package com.github.admins.services;

import com.github.admins.dto.BannerDto;
import com.github.admins.services.impl.BillsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(
        name = "advertising",
        fallback = BillsService.class,
        contextId = "advertisingId"
)
public interface IBannerService {

    @PostMapping(
            path = "/v1/banners/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    BannerDto create(@Valid @RequestBody BannerDto payload);

    @PutMapping(path = "/v1/banners/edit")
    @ResponseStatus(value = HttpStatus.OK)
    void update(@Valid @RequestBody BannerDto payload);

    @DeleteMapping(path = "/v1/banners/edit/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "id") Long id);

}
