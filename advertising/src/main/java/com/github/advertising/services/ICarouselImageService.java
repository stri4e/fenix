package com.github.advertising.services;

import com.github.advertising.entity.CarouselImage;

import java.util.List;

public interface ICarouselImageService {

    CarouselImage create(CarouselImage carouselImage);

    List<CarouselImage> readAllActive();

    void update(Long id, String image);

    void remove(Long id);

}
