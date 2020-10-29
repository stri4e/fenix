package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.products.entity.Subcategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for keeps catogory.")
public class CategoryDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -570839933846137004L;

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

    @ApiModelProperty(
            value = "Sub categories.")
    @JsonProperty(value = "subCategories")
    private List<Subcategory> subcategories;

    public CategoryDto(@NotBlank(message = "Category name is required") String name) {
        this.name = name;
    }

    public CategoryDto(@NotBlank(message = "Category name is required") String name, List<Subcategory> subcategories) {
        this.name = name;
        this.subcategories = subcategories;
    }

}
