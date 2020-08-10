package com.github.admins.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public abstract class Item implements Serializable, Cloneable {

    private static final long serialVersionUID = -7416895101550698061L;

    @JsonProperty(value = "id")
    private Long id;

    @NotBlank
    @JsonProperty(value = "name")
    private String name;

    @NotNull
    @JsonProperty(value = "price")
    private BigDecimal price;

    @NotNull
    @JsonProperty(value = "quantity")
    private Integer quantity;

    @NotBlank
    @JsonProperty(value = "description")
    private String description;

    @NotBlank
    @JsonProperty(value = "previewImage")
    private String previewImage;

    @NotEmpty
    @JsonProperty(value = "images")
    private List<String> images;

    @NotNull
    @JsonProperty(value = "createDate")
    private Date createDate;

    public Item() {
    }

    public Item(Long id, @NotBlank String name, @NotNull BigDecimal price,
                @NotNull Integer quantity, @NotBlank String description,
                @NotBlank String previewImage, @NotEmpty List<String> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.previewImage = previewImage;
        this.images = images;
    }

}
