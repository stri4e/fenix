package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -6943992056966122890L;

    @ApiModelProperty(
            value = "Brand id.",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(
            value = "Brand name.",
            example = "1"
    )
    @JsonProperty(value = "name")
    private String name;

}
