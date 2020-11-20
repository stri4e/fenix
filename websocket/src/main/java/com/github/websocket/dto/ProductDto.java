package com.github.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ProductDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -493878542027060198L;

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "brandName")
    @NotBlank(message = "Brand name is required.")
    private String brandName;

    @JsonProperty(value = "name")
    @NotBlank(message = "Name is required.")
    private String name;

    @JsonProperty(value = "price")
    @NotNull(message = "Price is required.")
    private BigDecimal price;

    @JsonProperty(value = "quantity")
    @NotNull(message = "Quantity is required.")
    private Integer quantity;

    @JsonProperty(value = "description")
    @NotBlank(message = "Description is required.")
    private String description;

    @JsonProperty(value = "previewImage")
    private String previewImage;

    @JsonProperty(value = "images")
    private List<String> images;

    @JsonProperty(value = "specifications")
    private List<SpecificationDto> specifications;

    @JsonProperty(value = "comments")
    private List<CommentDto> comments;

    @JsonProperty(value = "subcategoryName")
    private String subcategoryName;

    @ApiModelProperty(
            value = "Product comments"
    )
    @JsonProperty(value = "boughtCount")
    @NotNull(message = "BoughtCount Name is required.")
    private Integer boughtCount = 0;

}
