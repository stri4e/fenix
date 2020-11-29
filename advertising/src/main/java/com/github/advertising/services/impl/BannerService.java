package com.github.advertising.services.impl;

import com.github.advertising.entity.Banner;
import com.github.advertising.entity.EntityStatus;
import com.github.advertising.exceptions.NotFound;
import com.github.advertising.repository.BannerRepo;
import com.github.advertising.services.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BannerService implements IBannerService {

    private final BannerRepo bannerRepo;

    @Override
    public Banner create(Banner banner) {
        return this.bannerRepo.save(banner);
    }

    @Override
    public Banner readActive() {
        return this.bannerRepo.findByStatus(EntityStatus.on)
                .orElse(this.bannerRepo.findTop1ByStatus(EntityStatus.off)
                        .orElseThrow(NotFound::new));
    }

    @Override
    public void update(Long id, String image) {
        this.bannerRepo.update(id, image);
    }

    @Override
    public void remove(Long id) {
        this.bannerRepo.remove(id, EntityStatus.off);
    }

}
