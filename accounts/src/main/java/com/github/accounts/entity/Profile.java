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
@Table(name = "profiles", schema = "public")
public class Profile implements Serializable, Cloneable {

    private static final long serialVersionUID = -5333250276781691289L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "patronymic",
            nullable = false
    )
    private String patronymic;

    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Sex sex = Sex.unknown;

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

    public Profile(Long id, String firstName, String lastName, String patronymic, String dateOfBirth, Sex sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
    }

    public Profile(Long id, String firstName, String lastName, String patronymic, String dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
    }

    public static Profile profileDef() {
        return new Profile(
                null,
                "unknown",
                "unknown",
                "unknown",
                "unknown"
        );
    }

}
