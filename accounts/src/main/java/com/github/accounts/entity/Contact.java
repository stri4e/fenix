package com.github.accounts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts", schema = "public")
public class Contact implements Serializable, Cloneable {

    private static final long serialVersionUID = -5630927194373190406L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "phone",
            nullable = false,
            length = 150
    )
    private String phone;

    @Column(
            name = "company_name",
            nullable = false,
            length = 150
    )
    private String email;

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

    public Contact(Long id, String phone, String email) {
        this.id = id;
        this.phone = phone;
        this.email = email;
    }

    public static Contact contactDef() {
        return new Contact(
                null,
                "unknown",
                "unknown"
        );
    }

}
