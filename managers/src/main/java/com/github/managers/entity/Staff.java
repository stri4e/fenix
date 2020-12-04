package com.github.managers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(value = {
        @NamedQuery(
                name = "Staff.findByStatus",
                query = "select s from Staff s where s.status=:status"
        ),
        @NamedQuery(
                name = "Staff.findByManagerId",
                query = "select s from Staff s where s.managerId =:managerId"
        ),
})
@Table(
        name = "staffs",
        schema = "public",
        indexes = @Index(columnList = "email", name = "email_idx"),
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_staff_email",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "uk_staff_managerId",
                        columnNames = "manager_id"
                )
        }
)
public class Staff implements Serializable, Cloneable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            length = 100
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            length = 100
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            length = 100
    )
    private String email;

    @Column(
            name = "phone",
            nullable = false,
            length = 100
    )
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(
            name = "manager_id",
            nullable = false
    )
    private UUID managerId;

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

    public Staff(Long id, String firstName, String lastName, String email, String phone, String avatar, UUID managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.managerId = managerId;
        this.avatar = avatar;
    }
}
