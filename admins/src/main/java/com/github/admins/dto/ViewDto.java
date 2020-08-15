package com.github.admins.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "createAt")
    private Date createAt;

    @JsonProperty(value = "products")
    private List<ProductDto> products;

}
