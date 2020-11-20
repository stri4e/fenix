package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This model use for keep product.")
public class ProductDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -4851060288664633909L;

    @ApiModelProperty(
            value = "Product id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(
            value = "Product name",
            example = "Xiaomi"
    )
    @JsonProperty(value = "brandName")
    @NotBlank(message = "Brand name is required.")
    private String brandName;

    @ApiModelProperty(
            value = "Product name",
            example = "Xiaomi"
    )
    @JsonProperty(value = "name")
    @NotBlank(message = "Name is required.")
    private String name;

    @ApiModelProperty(
            value = "Product price",
            example = "25.000"
    )
    @JsonProperty(value = "price")
    @NotNull(message = "Price is required.")
    private BigDecimal price;

    @ApiModelProperty(
            value = "Product quantity",
            example = "250"
    )
    @JsonProperty(value = "quantity")
    @NotNull(message = "Quantity is required.")
    private Integer quantity;

    @ApiModelProperty(
            value = "Product description",
            example = "This product is supper useful"
    )
    @JsonProperty(value = "description")
    @NotBlank(message = "Description is required.")
    private String description;

    @ApiModelProperty(
            value = "Product preview image"
    )
    @JsonProperty(value = "previewImage")
    @NotBlank(message = "Preview Image is required.")
    private String previewImage;

    @ApiModelProperty(
            value = "Product images"
    )
    @JsonProperty(value = "images")
    private List<String> images;

    @ApiModelProperty(
            value = "Product specifications"
    )
    @JsonProperty(value = "specifications")
    private List<SpecificationDto> specifications;

    @ApiModelProperty(
            value = "Product comments"
    )
    @JsonProperty(value = "comments")
    private List<CommentDto> comments;

    @ApiModelProperty(
            value = "Product comments"
    )
    @JsonProperty(value = "subcategoryName")
    @NotBlank(message = "Subcategory Name is required.")
    private String subcategoryName;

    @ApiModelProperty(
            value = "Product comments"
    )
    @JsonProperty(value = "boughtCount")
    @NotNull(message = "BoughtCount Name is required.")
    private Integer boughtCount = 0;

}
