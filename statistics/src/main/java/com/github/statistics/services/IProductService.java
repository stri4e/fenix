package com.github.statistics.services;

import com.github.statistics.dto.ProductDto;
import com.github.statistics.services.impl.ProductService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "products",
        fallback = ProductService.class
)
public interface IProductService {

    @GetMapping(
            path = "/v1/fetch",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Optional<List<ProductDto>> readByIds(@RequestParam(name = "ids") List<Long> ids);

}
