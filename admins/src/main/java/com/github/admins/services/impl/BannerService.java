package com.github.admins.services.impl;

import com.github.admins.dto.BannerDto;
import com.github.admins.services.IBannerService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class BannerService implements IBannerService {

    @Override
    public BannerDto create(@Valid BannerDto payload) {
        return null;
    }

    @Override
    public void update(@Valid BannerDto payload) {

    }

    @Override
    public void delete(Long id) {

    }
}
