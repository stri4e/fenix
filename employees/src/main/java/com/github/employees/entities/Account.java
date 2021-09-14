package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = -7822776482285231836L;

    private Long id;

    private Profile profile;

    private Contact contact;

    private EmergencyContact emergencyContact;

    private Address address;

    // TODO: 14.09.21 may be
    private Employee employee;

    private EntityStatus status = EntityStatus.on;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

}
