package com.github.master.card.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts", schema = "public")
public class Account implements Serializable, Cloneable {

    private static final long serialVersionUID = 6259420060663577603L;

    @Id
    @GeneratedValue(
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(
            name = "partner_id",
            nullable = false,
            unique = true
    )
    private String partnerId;

    @Column(
            name = "consumer_key",
            nullable = false,
            unique = true
    )
    private String consumerKey;

    @Column(
            name = "key_alias",
            nullable = false,
            unique = true
    )
    private String keyAlias;

    @Column(
            name = "key_password",
            nullable = false,
            unique = true
    )
    private String keyPassword;

    @Column(
            name = "private_key",
            nullable = false,
            unique = true
    )
    private String privateKey;

    @Column(
            name = "uri_scheme",
            nullable = false
    )
    private String uriScheme;

    @Column(
            name = "identifier",
            nullable = false
    )
    private String identifier;

    @Column(
            name = "exp_year",
            nullable = false
    )
    private String expYear;

    @Column(
            name = "exp_month",
            nullable = false
    )
    private String expMonth;

    @Column(
            name = "cvc",
            nullable = false
    )
    private Integer cvc;

    @Column(
            name = "account_uri",
            nullable = false,
            unique = true
    )
    private String accountUri;

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
            name = "address",
            nullable = false
    )
    private String address;

    @Column(
            name = "city",
            nullable = false
    )
    private String city;

    @Column(
            name = "post_code",
            nullable = false
    )
    private Integer postCode;

    @Column(
            name = "post_code",
            unique = true
    )
    private String accountName;

    @OneToMany(
            targetEntity = Transaction.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "transaction_id"
    )
    private List<Transaction> transactions;

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

}
