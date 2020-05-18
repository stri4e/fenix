package com.github.orders.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "This model use for keep product detail.")
public class ProductDetailDto extends ProductDto {

    @ApiModelProperty(
            value = "Product specifications"
    )
    private List<SpecificationDto> specification;

    @ApiModelProperty(
            value = "Product comments"
    )
    private List<CommentDto> comments;

    @ApiModelProperty(
            value = "Product category"
    )
    private CategoryDto category;

    public ProductDetailDto(Long id,
                            @NotBlank(message = "Name is required.") String name,
                            @NotNull(message = "Price is required.") BigDecimal price,
                            @NotNull(message = "Quantity is required.") Integer quantity,
                            @NotBlank(message = "Description is required.") String description,
                            String previewImage, List<String> images,
                            List<SpecificationDto> specification,
                            List<CommentDto> comments,
                            CategoryDto category) {
        super(id, name, price, quantity, description, previewImage, images);
        this.specification = specification;
        this.comments = comments;
        this.category = category;
    }

}
