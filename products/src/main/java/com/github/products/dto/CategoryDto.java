package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for keeps catogory.")
public class CategoryDto {


    @ApiModelProperty(
            value = "Category id.",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(
            value = "Category name.",
            example = "phone")
    @JsonProperty(value = "name")
    @NotBlank(message = "Category name is required")
    private String name;

    public CategoryDto(@NotBlank(message = "Category name is required") String name) {
        this.name = name;
    }
}
