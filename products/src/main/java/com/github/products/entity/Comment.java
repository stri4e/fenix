package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment", schema = "public")
@NamedQueries(value = {
        @NamedQuery(
                name = "Comment.findAll",
                query = "SELECT c FROM Comment c"
        ),
        @NamedQuery(
                name = "Comment.findById",
                query = "SELECT c FROM Comment c WHERE c.id = :id"
        ),
        @NamedQuery(
                name = "Comment.findByName",
                query = "SELECT c FROM Comment c WHERE c.name = :name"
        )
})
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

}
