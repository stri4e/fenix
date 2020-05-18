package com.github.orders.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for keeps category.")
public class CategoryDto {

    @ApiModelProperty(
            value = "Category id.",
            example = "1"
    )
    private Long id;

    @ApiModelProperty(
            value = "Category name.",
            example = "phone"
    )
    @NotBlank(message = "Category name is required")
    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }

}
