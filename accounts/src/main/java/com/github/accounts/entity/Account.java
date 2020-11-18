package com.github.accounts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "views")
@Table(name = "accounts", schema = "public")
public class Account implements Serializable, Cloneable {

    private static final long serialVersionUID = -7822776482285231836L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private UUID userId;

    @OneToOne(
            targetEntity = Profile.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "profile_id",
            foreignKey = @ForeignKey(
                    name = "account_profile_fk"
            )
    )
    private Profile profile;

    @OneToOne(
            targetEntity = Contact.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "contact_id",
            foreignKey = @ForeignKey(
                    name = "account_contact_fk"
            )
    )
    private Contact contact;

    @OneToOne(
            targetEntity = Address.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "address_id",
            foreignKey = @ForeignKey(
                    name = "account_address_fk"
            )
    )
    private Address address;

    @OneToMany(
            targetEntity = View.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "view_id",
            foreignKey = @ForeignKey(
                    name = "accounts_view_fk"
            )
    )
    @OrderBy(value = "updateAt DESC")
    private Set<View> views = new HashSet<>();

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

    public Account view(View id) {
        this.views.add(id);
        return this;
    }

    public boolean isViewsEmpty() {
        return Objects.isNull(this.views) || this.views.isEmpty();
    }

    public Account(UUID userId, Profile profile, Contact contact, Address address) {
        this.userId = userId;
        this.profile = profile;
        this.contact = contact;
        this.address = address;
    }

    public Account(UUID userId, Profile profile, Contact contact, Address address, Set<View> views) {
        this.userId = userId;
        this.profile = profile;
        this.contact = contact;
        this.address = address;
        this.views = views;
    }

}
