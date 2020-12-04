package com.github.advertising.controllers.impl;

import com.github.advertising.controllers.ICarouselImageController;
import com.github.advertising.dto.CarouselImageDto;
import com.github.advertising.services.ICarouselImageService;
import com.github.advertising.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.advertising.utils.TransferObj.fromCarouselImage;
import static com.github.advertising.utils.TransferObj.toCarouselImage;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/carousel")
public class CarouselImageController implements ICarouselImageController {

    private final ICarouselImageService carouselImageService;

    @Override
    public List<CarouselImageDto> findAllActive() {
        return this.carouselImageService.readAllActive().stream()
                .map(TransferObj::fromCarouselImage)
                .collect(Collectors.toList());
    }

    @Override
    public CarouselImageDto save(@Valid CarouselImageDto payload) {
        return fromCarouselImage(
                this.carouselImageService.create(toCarouselImage(payload))
        );
    }

    @Override
    public void update(@Valid CarouselImageDto payload) {
        this.carouselImageService.update(payload.getId(), payload.getImage());
    }

    @Override
    public void remove(Long id) {
        this.carouselImageService.remove(id);
    }
}
