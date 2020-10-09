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

    public static final String DEFAULT_CONTRACT_NAME = "default";

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
            name = "full_name",
            nullable = false
    )
    private String fullName;

    @Column(
            name = "address",
            nullable = false
    )
    private String address;

    @Column(
            name = "pow",
            nullable = false
    )
    private Integer pow;

    @Enumerated(value = EnumType.STRING)
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
            String fullName,
            String address,
            Integer pow) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.address = address;
        this.status = EntityStatus.on;
        this.pow = pow;
    }

}
