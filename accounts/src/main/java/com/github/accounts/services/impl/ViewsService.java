package com.github.accounts.services.impl;

import com.github.accounts.entity.View;
import com.github.accounts.repository.ViewsRepo;
import com.github.accounts.services.IViewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ViewsService implements IViewsService {

    private final ViewsRepo viewsRepo;

    @Override
    public boolean existsByProductId(Long productId) {
        return this.viewsRepo.existsByProductId(productId);
    }

    @Override
    public View readByProductId(Long productId) {
        return this.viewsRepo.findByProductId(productId);
    }

    @Override
    public View createOrUpdate(View v) {
        return this.viewsRepo.save(v);
    }
}
