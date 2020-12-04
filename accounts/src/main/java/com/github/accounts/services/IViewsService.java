package com.github.accounts.services;

import com.github.accounts.entity.View;

public interface IViewsService {

    boolean existsByProductId(Long productId);

    View readByProductId(Long productId);

    View createOrUpdate(View v);

}
