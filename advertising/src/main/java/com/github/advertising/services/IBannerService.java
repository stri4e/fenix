package com.github.advertising.services;

import com.github.advertising.entity.Banner;
import com.github.advertising.entity.EntityStatus;

public interface IBannerService {

    Banner create(Banner banner);

    Banner readActive();

    void update(Long id, String image);

    void remove(Long id);

}
