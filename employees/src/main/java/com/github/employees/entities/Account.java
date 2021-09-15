package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = -7822776482285231836L;

    @Id
    private String id;

    private Profile profile;

    private Contact contact;

    private EmergencyContact emergencyContact;

    private Address address;

    private UUID employeeId;

    private EntityStatus status = EntityStatus.on;

    @CreatedBy
    private UUID createBy;

    @LastModifiedDate
    private UUID updateBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

}
