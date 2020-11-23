package com.github.statistics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "logins", schema = "public")
public class Login implements Serializable, Cloneable {

    private static final long serialVersionUID = -7852042550949529200L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "ip",
            nullable = false
    )
    private String token;

    @Column(
            name = "ip",
            nullable = false
    )
    private String ip;

    @ElementCollection(
            fetch = FetchType.EAGER
    )
    private Map<String, String> information;

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

    public Login(Long id, String token, String ip, Map<String, String> information) {
        this.id = id;
        this.token = token;
        this.ip = ip;
        this.information = information;
    }

}
