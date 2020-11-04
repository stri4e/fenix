package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -5540760378245517731L;

    @ApiModelProperty(
            value = "Company id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(
            value = "Company name",
            example = "Novaposhta"
    )
    @JsonProperty(value = "name")
    private String name;

}
