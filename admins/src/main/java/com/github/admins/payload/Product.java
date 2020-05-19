package com.github.admins.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

}
