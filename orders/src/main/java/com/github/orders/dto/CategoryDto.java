package com.github.orders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for keeps category.")
public class CategoryDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 4900184865477114578L;

    @ApiModelProperty(
            value = "Category id.",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(
            value = "Category name.",
            example = "phone"
    )
    @JsonProperty(value = "name")
    @NotBlank(message = "Category name is required")
    private String name;

}
