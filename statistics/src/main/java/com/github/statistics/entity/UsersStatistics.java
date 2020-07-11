package com.github.statistics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(
        name = "users_statistic",
        schema = "public"
)
@AllArgsConstructor
@NoArgsConstructor
public class UsersStatistics implements Serializable, Cloneable {

    private static final long serialVersionUID = 5013043155046061246L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "userId",
            nullable = false,
            unique =   true
    )
    private Long userId;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(
            name = "lastLoginDate",
            nullable = false
    )
    private Date lastLoginDate;

    @Column(
            name = "count",
            nullable = false
    )
    private Integer count;

    public UsersStatistics(Long userId, Date lastLoginDate, Integer count) {
        this.userId = userId;
        this.lastLoginDate = lastLoginDate;
        this.count = count;
    }

    public UsersStatistics(Date lastLoginDate, Integer count) {
        this.lastLoginDate = lastLoginDate;
        this.count = count;
    }

}
