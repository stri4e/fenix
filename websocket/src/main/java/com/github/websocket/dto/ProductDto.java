package com.github.websocket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    @NotBlank(message = "Name is required.")
    private String name;

    @JsonProperty(value = "price")
    @NotNull(message = "Price is required.")
    private BigDecimal price;

    @JsonProperty(value = "quantity")
    @NotNull(message = "Quantity is required.")
    private Integer quantity;

    @JsonProperty(value = "description")
    @NotBlank(message = "Description is required.")
    private String description;

    @JsonProperty(value = "previewImage")
    private String previewImage;

    @JsonProperty(value = "images")
    private List<String> images;

}
