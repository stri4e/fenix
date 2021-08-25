package com.github.products.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductDetailsDto extends ProductDto implements Serializable {

    private static final long serialVersionUID = -4031169770161001099L;

    private List<StocksQuantityDto> links;

    private List<CriteriaDto> criteria;

}
