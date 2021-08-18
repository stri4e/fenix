package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specification_sections", schema = "public")
public class SpecSection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(
            fetch = FetchType.EAGER,
            targetEntity = Title.class,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            foreignKey = @ForeignKey(
                    name = "spec_section_title_fk"
            )
    )
    private Title title;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "specSections",
            cascade = CascadeType.MERGE,
            targetEntity = Specification.class
    )
    private Set<Specification> specifications;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            targetEntity = Product.class
    )
    @JoinColumn(
            nullable = false,
            name = "product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "product_spec_section_fk"
            )
    )
    private Product product;

}
