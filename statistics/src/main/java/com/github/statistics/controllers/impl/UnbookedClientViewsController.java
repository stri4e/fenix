package com.github.statistics.controllers.impl;

import com.github.statistics.controllers.IUnbookedClientViewsController;
import com.github.statistics.dto.ProductDto;
import com.github.statistics.entity.UnbookedClientView;
import com.github.statistics.exceptions.NotFound;
import com.github.statistics.services.IProductService;
import com.github.statistics.services.IUnbookedClientViewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/unbooked")
public class UnbookedClientViewsController implements IUnbookedClientViewsController {

    private final IUnbookedClientViewsService unbookedClientViewService;

    private final IProductService productService;

    @Override
    public void save(String ip, Long productId) {
        this.unbookedClientViewService.create(
                new UnbookedClientView(ip, productId)
        );
    }

    @Override
    public List<ProductDto> findByIp(String ip) {
        return this.productService.readByIds(
                this.unbookedClientViewService.read(ip).stream()
                        .map(UnbookedClientView::getProductId)
                        .collect(Collectors.toList())
        ).orElseThrow(NotFound::new);
    }

}
