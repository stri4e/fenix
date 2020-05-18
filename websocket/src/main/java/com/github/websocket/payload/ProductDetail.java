package com.github.websocket.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends Product {

    private List<Specification> specification;

    private List<Comment> comments;

    private Category category;

    public ProductDetail(Long id,
                         @NotBlank(message = "Name is required.") String name,
                         @NotNull(message = "Price is required.") BigDecimal price,
                         @NotNull(message = "Quantity is required.") Integer quantity,
                         @NotBlank(message = "Description is required.") String description,
                         String previewImage, List<String> images,
                         List<Specification> specification,
                         List<Comment> comments,
                         Category category) {
        super(id, name, price, quantity, description, previewImage, images);
        this.specification = specification;
        this.comments = comments;
        this.category = category;
    }

}
