package com.github.advertising.services.impl;

import com.github.advertising.entity.CarouselImage;
import com.github.advertising.entity.EntityStatus;
import com.github.advertising.repository.CarouselImageRepo;
import com.github.advertising.services.ICarouselImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarouselImageService implements ICarouselImageService {

    private final CarouselImageRepo carouselImageRepo;

    @Override
    public CarouselImage create(CarouselImage carouselImage) {
        return this.carouselImageRepo.save(carouselImage);
    }

    @Override
    public List<CarouselImage> readAllActive() {
        return this.carouselImageRepo.findByStatus(EntityStatus.on);
    }

    @Override
    public void update(Long id, String image) {
        this.carouselImageRepo.update(id, image);
    }

    @Override
    public void remove(Long id) {
        this.carouselImageRepo.remove(id, EntityStatus.off);
    }

}
