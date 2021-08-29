package com.github.products.entity;

import lombok.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Proportions implements Serializable {

    private static final long serialVersionUID  = 4023461669135689430L;

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "width",
            nullable = false
    )
    private Integer width;

    @Column(
            name = "height",
            nullable = false
    )
    private Integer height;

    @Column(
            name = "weight",
            nullable = false
    )
    private Integer weight;

    @Column(
            name = "depth"
    )
    private Integer depth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proportions)) return false;
        Proportions that = (Proportions) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(width, that.width) &&
                Objects.equals(height, that.height) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(depth, that.depth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, width, height, weight, depth);
    }
}
