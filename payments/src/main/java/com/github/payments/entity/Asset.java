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
@NamedQueries(value = {
        @NamedQuery(
                name = "Asset.findById",
                query = "select a from Asset a where a.id=:id"
        ),
        @NamedQuery(
                name = "Asset.findByName",
                query = "select a from Asset a where a.name=:name"
        ),
        @NamedQuery(
                name = "Asset.findAllByStatus",
                query = "select a from Asset a where a.status=:status"
        ),
        @NamedQuery(
                name = "Asset.findAllByAssetType",
                query = "select a from Asset a where a.assetType=:assetType"
        )
})
@Table(
        name = "assets",
        schema = "public",
        indexes = @Index(columnList = "name", name = "asset_name_idx"),
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_asset_name",
                        columnNames = "name"
                ),
                @UniqueConstraint(
                        name = "uk_asset_full_name",
                        columnNames = "full_name"
                )
        }
)
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
            nullable = false
    )
    private String name;

    @Column(
            name = "full_name",
            nullable = false
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

    public Asset(String owner, String name, String fullName, Integer pow, AssetType assetType) {
        this.owner = owner;
        this.name = name;
        this.fullName = fullName;
        this.pow = pow;
        this.assetType = assetType;
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
