package com.github.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "This model use for keeps sub category.")
public class SubcategoryDto implements Serializable {

    private static final long serialVersionUID = -7600745435217894495L;

    @ApiModelProperty(
            value = "Sub category id",
            example = "1"
    )
    @JsonProperty(value = "id")
    private Long id;

    @ApiModelProperty(
            value = "sub category name",
            example = "Xiaomi"
    )
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(
            value = "filters"
    )
    @JsonProperty(value = "filters")
    private List<FilterDto> filters;

    public SubcategoryDto(String name) {
        this.name = name;
    }

    public SubcategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SubcategoryDto(String name, List<FilterDto> filters) {
        this.name = name;
        this.filters = filters;
    }

    public List<FilterDto> getFilters() {
        return Objects.requireNonNullElse(filters, new ArrayList<>());
    }
}
