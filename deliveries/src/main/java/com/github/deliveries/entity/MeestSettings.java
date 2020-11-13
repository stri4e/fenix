package com.github.deliveries.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meest_settings", schema = "public")
public class MeestSettings implements Serializable, Cloneable {

    private static final long serialVersionUID = -3318871907625867457L;

    @Id
    @Column(
            name = "ID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "base_url",
            nullable = false
    )
    private String baseUrl;

    @OneToOne(
            targetEntity = MeestSession.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "meest_session_id"
    )
    private MeestSession session;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> header = new HashMap<>();

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
            nullable = false
    )
    private LocalDateTime updateAt;

    public MeestSettings(String baseUrl, Map<String, String> header) {
        this.baseUrl = baseUrl;
        this.header = header;
    }

}
