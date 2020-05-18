package com.github.websocket.payload;

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
public class Product {

    private Long id;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Price is required.")
    private BigDecimal price;

    @NotNull(message = "Quantity is required.")
    private Integer quantity;

    @NotBlank(message = "Description is required.")
    private String description;

    private String previewImage;

    private List<String> images;

}
