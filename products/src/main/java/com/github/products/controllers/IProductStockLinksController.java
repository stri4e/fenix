package com.github.products.controllers;

import com.github.products.dto.ProductStockLinkDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

public interface IProductStockLinksController {

    @PostMapping(path = "/edit/links/{productId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    void saveAllLinks(
            @PathVariable(name = "productId") Long productId,
            @NotEmpty @RequestBody Map<Long, Integer> quantityGroupByStockId
    );

    @PostMapping(path = "/edit/link/{productId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    void saveLink(@Valid @RequestBody ProductStockLinkDto payload);

    @DeleteMapping("/edit/link/{linkId}")
    void removeLink(@PathVariable(name = "linkId") Long linkId);

}
