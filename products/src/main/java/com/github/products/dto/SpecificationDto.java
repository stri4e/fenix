package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for keep specification.")
public class SpecificationDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 8393941766603142335L;

    @ApiModelProperty(
            value = "Specification id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(
            value = "Specification name",
            example = "Power"
    )
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(
            value = "Specification description",
            example = "Supper powerful device"
    )
    @JsonProperty(value = "description")
    private String description;

}
