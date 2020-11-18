package com.github.admins.services.impl;

import com.github.admins.dto.BrandDto;
import com.github.admins.services.IBrandsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandsService implements IBrandsService {

    @Override
    public Optional<BrandDto> create(BrandDto payload) {
        return Optional.empty();
    }

    @Override
    public Optional<BrandDto> readByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<List<BrandDto>> readAllByStatus(String status) {
        return Optional.empty();
    }

    @Override
    public void update(BrandDto payload) {

    }

    @Override
    public void delete(Long id) {

    }
}
