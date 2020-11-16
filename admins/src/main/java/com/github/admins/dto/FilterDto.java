package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for keeps filter information.")
public class FilterDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -9009582168098182244L;

    @ApiModelProperty(
            value = "Filter id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(
            value = "filter title",
            example = "power"
    )
    @JsonProperty(value = "title")
    private String title;

    @ApiModelProperty(
            value = "criteria"
    )
    @JsonProperty(value = "criteria")
    private List<CriteriaDto> criteria;

}
