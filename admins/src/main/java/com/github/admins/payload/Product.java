package com.github.admins.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Product extends Item implements Serializable, Cloneable {

    private static final long serialVersionUID = -3490371538827798606L;

    @JsonProperty(value = "specification")
    private Set<Specification> specification = new HashSet<>();

    @JsonProperty(value = "comments")
    private Set<Comment> comments = new HashSet<>();

    @JsonProperty(value = "category")
    private Category category;

    @JsonProperty(value = "publish")
    private ProductStatus publish;

    public void addSpecification(Specification s) {
        if (Objects.nonNull(s)) {
            this.specification.add(s);
        }
    }

    public void addComment(Comment c) {
        if (Objects.nonNull(c)) {
            this.comments.add(c);
        }
    }

    public Product() {
    }

    public Product(Long id, @NotBlank String name,
                   @NotNull BigDecimal price, @NotNull Integer quantity,
                   @NotBlank String description, @NotBlank String previewImage,
                   @NotEmpty List<String> images) {
        super(id, name, price, quantity, description, previewImage, images);
    }
}
