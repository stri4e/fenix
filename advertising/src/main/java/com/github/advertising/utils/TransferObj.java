package com.github.advertising.utils;

import com.github.advertising.dto.BannerDto;
import com.github.advertising.dto.CarouselImageDto;
import com.github.advertising.entity.Banner;
import com.github.advertising.entity.CarouselImage;

public class TransferObj {

    public static BannerDto fromBanner(Banner data) {
        return new BannerDto(
                data.getId(),
                data.getImage()
        );
    }

    public static Banner toBanner(BannerDto data) {
        return new Banner(
                data.getId(),
                data.getImage()
        );
    }

    public static CarouselImage toCarouselImage(CarouselImageDto data) {
        return new CarouselImage(
                data.getId(),
                data.getImage()
        );
    }

    public static CarouselImageDto fromCarouselImage(CarouselImage data) {
        return new CarouselImageDto(
                data.getId(),
                data.getImage()
        );
    }

}
