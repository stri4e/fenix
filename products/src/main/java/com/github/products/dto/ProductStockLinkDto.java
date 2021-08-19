package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Deprecated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockLinkDto implements Serializable {

    private static final long serialVersionUID = -6670307876264598614L;

    @ApiModelProperty(
            value = "Product id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long productId;

    @ApiModelProperty(
            value = "Stock Id",
            example = "1"
    )
    @JsonProperty(value = "stockId")
    private Long stockId;

    @ApiModelProperty(
            value = "quantity",
            example = "10"
    )
    @JsonProperty(value = "quantity")
    private Integer quantity;

}
