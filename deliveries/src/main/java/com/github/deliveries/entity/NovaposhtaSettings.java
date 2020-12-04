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
@Table(name = "novaposhta_settings", schema = "public")
public class NovaposhtaSettings implements Serializable, Cloneable {

    private static final long serialVersionUID = -3206352516169737016L;

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

    @Column(
            name = "api_key",
            nullable = false
    )
    private String apiKey;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> headers = new HashMap<>();

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

    public NovaposhtaSettings(Long id, String baseUrl, String apiKey, Map<String, String> headers) {
        this.id = id;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.headers = headers;
    }

}
