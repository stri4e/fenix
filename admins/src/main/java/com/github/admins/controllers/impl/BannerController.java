package com.github.admins.controllers.impl;

import com.github.admins.controllers.IBannerController;
import com.github.admins.dto.BannerDto;
import com.github.admins.services.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/banners")
public class BannerController implements IBannerController {

    private final IBannerService bannerService;

    @Override
    public BannerDto save(@Valid BannerDto payload) {
        return this.bannerService.create(payload);
    }

    @Override
    public void update(@Valid BannerDto payload) {
        this.bannerService.update(payload);
    }

    @Override
    public void remove(Long id) {
        this.bannerService.delete(id);
    }
}
