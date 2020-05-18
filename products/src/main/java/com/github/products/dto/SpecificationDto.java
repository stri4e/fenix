package com.github.products.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for keep specification.")
public class SpecificationDto {

    @ApiModelProperty(
            value = "Specification id",
            example = "1")
    private Long id;
    @ApiModelProperty(
            value = "Specification name",
            example = "Power")
    private String name;
    @ApiModelProperty(
            value = "Specification description",
            example = "Supper powerful device")
    private String description;

}
