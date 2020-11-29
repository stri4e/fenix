package com.github.advertising.controllers.impl;

import com.github.advertising.controllers.IBannerController;
import com.github.advertising.dto.BannerDto;
import com.github.advertising.services.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.advertising.utils.TransferObj.fromBanner;
import static com.github.advertising.utils.TransferObj.toBanner;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/banners")
public class BannerController implements IBannerController {

    private final IBannerService bannerService;

    @Override
    public BannerDto findActive() {
        return fromBanner(this.bannerService.readActive());
    }

    @Override
    public BannerDto save(@Valid BannerDto payload) {
        return fromBanner(this.bannerService.create(toBanner(payload)));
    }

    @Override
    public void update(@Valid BannerDto payload) {
        this.bannerService.update(payload.getId(), payload.getImage());
    }

    @Override
    public void remove(Long id) {
        this.bannerService.remove(id);
    }

}
