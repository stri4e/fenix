package com.github.admins.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model use for keep product.")
public class ProductDto {

    @ApiModelProperty(
            value = "Product id",
            example = "1"
    )
    private Long id;

    @ApiModelProperty(
            value = "Product name",
            example = "Xiaomi"
    )
    @NotBlank(message = "Name is required.")
    private String name;

    @ApiModelProperty(
            value = "Product price",
            example = "25.000"
    )
    @NotNull(message = "Price is required.")
    private BigDecimal price;

    @ApiModelProperty(
            value = "Product quantity",
            example = "250"
    )
    @NotNull(message = "Quantity is required.")
    private Integer quantity;

    @ApiModelProperty(
            value = "Product description",
            example = "This product is supper useful"
    )
    @NotBlank(message = "Description is required.")
    private String description;

    @ApiModelProperty(
            value = "Product preview image"
    )
    private String previewImage;

    @ApiModelProperty(
            value = "Product images"
    )
    private List<String> images;

}
