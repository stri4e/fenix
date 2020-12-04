package com.github.admins.services;

import com.github.admins.dto.CarouselImageDto;
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
public interface ICarouselImageService {

    @PostMapping(
            path = "/edit",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    CarouselImageDto create(@Valid @RequestBody CarouselImageDto payload);

    @PutMapping(path = "/edit")
    @ResponseStatus(value = HttpStatus.OK)
    void update(@Valid @RequestBody CarouselImageDto payload);

    @DeleteMapping(path = "/edit/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "id") Long id);

}
