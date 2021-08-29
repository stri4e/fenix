package com.github.products.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specification", schema = "public")
@NamedQueries(value = {
        @NamedQuery(
                name = "Specification.findAll",
                query = "select s from Specification s"
        ),
        @NamedQuery(
                name = "Specification.findById",
                query = "select s from Specification s where s.id = :id"
        ),
        @NamedQuery(
                name = "Specification.findByName",
                query = "select s from Specification s where s.name = :name"
        )
})
public class Specification implements Serializable {

    private static final long serialVersionUID = -8426888996223798372L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            length = 50
    )
    private String name;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Subcategory.class,
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            nullable = false,
            name = "subcategory_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "subcategory_filter_fk"
            )
    )
    private Subcategory subcategory;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            targetEntity = SpecSection.class
    )
    @JoinTable(
            name = "spec_section_specifications",
            joinColumns = @JoinColumn(
                    name = "spec_section_id",
                    foreignKey = @ForeignKey(
                            name = "spec_section_specification_fk"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "specification_id",
                    foreignKey = @ForeignKey(
                            name = "specification_spec_section_fk"
                    )
            )
    )
    private Set<SpecSection> specSections;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    @CreationTimestamp
    @Column(
            name = "create_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(
            name = "update_at",
            nullable = false
    )
    private LocalDateTime updateAt;

    public Specification(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Specification(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Specification addSubcategory(Subcategory subcategory) {
        if (Objects.nonNull(subcategory)) {
            this.subcategory = subcategory;
            subcategory.addSpecification(this);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Specification)) return false;
        Specification that = (Specification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(subcategory, that.subcategory) &&
                Objects.equals(specSections, that.specSections) &&
                status == that.status &&
                Objects.equals(createAt, that.createAt) &&
                Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, createAt, updateAt);
    }
}
