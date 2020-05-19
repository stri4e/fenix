package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
