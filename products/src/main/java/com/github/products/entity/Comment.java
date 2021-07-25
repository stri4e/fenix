package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
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
        ),
        @NamedQuery(
                name = "Comment.findByName",
                query = "select c from Comment c where c.author = :name"
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

    @Column(
            name = "author",
            nullable = false
    )
    private String author;

    @Column(
            name = "text",
            nullable = false
    )
    private String text;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
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

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public Comment(Long id, String author, String text) {
        this.id = id;
        this.author = author;
        this.text = text;
    }
}
