package com.github.products.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(value = {
        @NamedQuery(
                name = "Comment.findAll",
                query = "select c from Comment c"
        ),
        @NamedQuery(
                name = "Comment.findById",
                query = "select c from Comment c where c.id = :id"
        )
})
@Table(name = "comment", schema = "public")
public class Comment implements Serializable {

    private static final long serialVersionUID = -7686291621942651724L;

    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(
            name = "text",
            nullable = false
    )
    private String text;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            targetEntity = Product.class
    )
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "product_comments_fk"
            )
    )
    private Product product;

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

    public Comment(Long id, String firstName, String lastName, UUID userId, String text) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.text = text;
    }

    public Comment(String firstName, String lastName, UUID userId, String text) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.text = text;
    }

    public Comment addProduct(Product product) {
        if (Objects.nonNull(product)) {
            this.product = product;
            product.addComment(this);
        }
        return this;
    }

}
