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
@Table(name = "whoms", schema = "public")
public class Whom implements Serializable, Cloneable {

    private static final long serialVersionUID = -1753110847212718718L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "firs_name",
            nullable = false
    )
    private String firsName;

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

    public Whom(String firsName, String lastName, String patronymic) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    public Whom(Long id,String firsName, String lastName, String patronymic) {
        this.id = id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }
}
