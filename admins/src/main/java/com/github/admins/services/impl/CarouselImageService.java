package com.github.admins.services.impl;

import com.github.admins.dto.CarouselImageDto;
import com.github.admins.services.ICarouselImageService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class CarouselImageService implements ICarouselImageService {

    @Override
    public CarouselImageDto create(@Valid CarouselImageDto payload) {
        return null;
    }

    @Override
    public void update(@Valid CarouselImageDto payload) {

    }

    @Override
    public void delete(Long id) {

    }
}
