package com.github.payments.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assets", schema = "public")
public class Asset implements Serializable, Cloneable {

    private static final long serialVersionUID = 1926778909238058312L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "owner",
            nullable = false
    )
    private String owner;

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            name = "full_name",
            nullable = false,
            unique = true
    )
    private String fullName;

    @Column(
            name = "pow",
            nullable = false
    )
    private Integer pow;

    @Column(
            name = "asset_type",
            nullable = false
    )
    @Enumerated(value = EnumType.STRING)
    private AssetType assetType;

    @Column(
            name = "status",
            nullable = false
    )
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

    public Asset(String name, String fullName, Integer pow) {
        this.name = name;
        this.fullName = fullName;
        this.pow = pow;
    }

    public Asset(String name, String fullName, Integer pow, EntityStatus status) {
        this.name = name;
        this.fullName = fullName;
        this.pow = pow;
        this.status = status;
    }

    public Asset(String name, String fullName, Integer pow, AssetType assetType, EntityStatus status) {
        this.name = name;
        this.fullName = fullName;
        this.pow = pow;
        this.assetType = assetType;
        this.status = status;
    }

    public Asset(Long id, String owner, String name, String fullName, Integer pow, AssetType assetType) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.fullName = fullName;
        this.pow = pow;
        this.assetType = assetType;
    }
}
