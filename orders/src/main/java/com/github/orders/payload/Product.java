package com.github.orders.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Product extends Item implements Serializable, Cloneable {

    private static final long serialVersionUID = -3490371538827798606L;

    @NotEmpty
    @JsonProperty(value = "specification")
    private Set<Specification> specification;

    @NotEmpty
    @JsonProperty(value = "comments")
    private Set<Comment> comments;

    @NotNull
    @JsonProperty(value = "category")
    private Category category;

    @NotNull
    @JsonProperty(value = "status")
    private ProductStatus status;

}
