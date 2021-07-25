package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {

    @JsonProperty(value = "quantityGroupByStockId")
    private Map<Long, Integer> quantityGroupByStockId;

    @JsonProperty(value = "product")
    private ProductDto product;

}
