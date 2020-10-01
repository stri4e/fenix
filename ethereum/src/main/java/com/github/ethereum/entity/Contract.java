package com.github.ethereum.entity;

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
@Table(name = "contracts", schema = "public")
public class Contract implements Serializable, Cloneable {

    private static final long serialVersionUID = -1028569186091960062L;

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
            name = "address",
            nullable = false
    )
    private String address;

    @Enumerated(value = EnumType.STRING)
    private EntityStatus status;

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

    public Contract(
            Long id,
            String name,
            String address,
            EntityStatus status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
    }

    public Contract(
            Long id,
            String name,
            String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = EntityStatus.on;
    }

}
