package com.github.products.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "filters", schema = "public")
public class Filter implements Serializable, Cloneable {

    private static final long serialVersionUID = 7706390004430335311L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "title",
            nullable = false,
            length = 50
    )
    private String title;

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private List<String> criteria = new ArrayList<>();

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

    public Filter(Long id, String title, List<String> criteria) {
        this.id = id;
        this.title = title;
        this.criteria = criteria;
    }

}
