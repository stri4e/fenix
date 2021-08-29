package com.github.products.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

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

}
