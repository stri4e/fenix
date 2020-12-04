package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
                query = "select c from Comment c where c.name = :name"
        )
})
@Table(name = "comment", schema = "public")
public class Comment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "comment",
            nullable = false
    )
    private String comment;

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

    public Comment(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public Comment(Long id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
    }
}
