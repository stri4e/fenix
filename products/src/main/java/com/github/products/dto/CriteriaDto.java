package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaDto implements Serializable {

    private static final long serialVersionUID = -8395912449017126288L;

    @ApiModelProperty(
            value = "Criteria id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @NotBlank(message = "Criteria value is required.")
    @ApiModelProperty(
            value = "criteria value",
            example = "5rt7"
    )
    @JsonProperty(value = "value")
    private String value;

    public CriteriaDto(@NotBlank(message = "Criteria value is required.") String value) {
        this.value = value;
    }
}
