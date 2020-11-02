package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for keeps statistic bought information.")
public class BoughtCountDto {

    @ApiModelProperty(
            value = "Product id",
            example = "1"
    )
    @JsonProperty(value = "productId")
    private Long productId;

    @ApiModelProperty(
            value = "Product bought count",
            example = "1.2"
    )
    @JsonProperty(value = "boughtCount")
    private int boughtCount;

}
