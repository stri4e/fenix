package com.github.deliveries.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "novaposta_legal_conterparty", schema = "public")
public class NovaposhtaLegalCounterparty {

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "ref",
            nullable = false
    )
    private String ref;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "middle_name",
            nullable = false
    )
    private String middleName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "counterparty",
            nullable = false
    )
    private String counterparty;

    @Column(
            name = "ownership_form",
            nullable = false
    )
    private String ownershipForm;

    @Column(
            name = "edrpou",
            nullable = false
    )
    private String edrpou;

    @Column(
            name = "counterparty_type",
            nullable = false
    )
    private String counterpartyType;

    @Column(
            name = "status",
            nullable = false
    )
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
            nullable = false,
            updatable = false
    )
    private LocalDateTime updateAt;

    public NovaposhtaLegalCounterparty(
            Long id,
            String ref,
            String description,
            String firstName,
            String middleName,
            String lastName,
            String counterparty,
            String ownershipForm,
            String edrpou,
            String counterpartyType
    ) {
        this.id = id;
        this.ref = ref;
        this.description = description;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.counterparty = counterparty;
        this.ownershipForm = ownershipForm;
        this.edrpou = edrpou;
        this.counterpartyType = counterpartyType;
    }


}
