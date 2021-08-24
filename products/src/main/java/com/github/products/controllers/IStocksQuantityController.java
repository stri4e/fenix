package com.github.products.controllers;

import com.github.products.dto.StocksQuantityDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public interface IStocksQuantityController {

    @PostMapping(path = "/edit/all/{productId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    void saveAll(
            @PathVariable(name = "productId") Long productId,
            @NotEmpty @RequestBody List<StocksQuantityDto> payload
    );

    @PostMapping(path = "/edit/{productId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    void save(
            @PathVariable(name = "productId") Long productId,
            @Valid @RequestBody StocksQuantityDto payload
    );

    @GetMapping(path = "/fetch/{productId}")
    List<StocksQuantityDto> findByProductId(@PathVariable(value = "productId") Long productId);

    @PutMapping(path = "/edit/{id}/{quantity}")
    void updateQuantity(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "quantity") Integer quantity
    );

    @DeleteMapping("/edit/{id}")
    void remove(@PathVariable(name = "id") Long id);

}
