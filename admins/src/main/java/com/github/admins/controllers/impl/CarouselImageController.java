package com.github.admins.controllers.impl;

import com.github.admins.controllers.ICarouselImageController;
import com.github.admins.dto.CarouselImageDto;
import com.github.admins.services.ICarouselImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/carousel")
public class CarouselImageController implements ICarouselImageController {

    private final ICarouselImageService carouselImageService;

    @Override
    public CarouselImageDto save(@Valid CarouselImageDto payload) {
        return this.carouselImageService.create(payload);
    }

    @Override
    public void update(@Valid CarouselImageDto payload) {
        this.carouselImageService.update(payload);
    }

    @Override
    public void remove(Long id) {
        this.carouselImageService.delete(id);
    }
}
